import java.util.*;
//Examen practico, ejercicio 1
//@Maciej Jacek Gyurkowich
public class Main{

    public static void main(String[] args){

        String nombre;
        String fecha;
        char genero;
        Miembro persona;
        Miembro padre;
        int num_hijos;


        //arbol de prueba
        Miembro m0 = new Miembro ("Ricardo", null,'H', "12-01-1945");
        Miembro m1 = new Miembro ("Jaime", m0, 'H', "23-07-1969");
        Miembro m2 = new Miembro ("Monica", m0, 'M', "16-08-1978");
        Miembro m3 = new Miembro ("Matias", m1, 'H', "01-12-1988");
        m0.addHijo(m1);
        m0.addHijo(m2);
        m1.addHijo(m3);

        Arbol arbol = new Arbol(); //arbol de prueba

        arbol.addMiembro(m0);
        arbol.addMiembro(m1);
        arbol.addMiembro(m2);
        arbol.addMiembro(m3);

        System.out.print("\n+------- Arbol Genealogico -------+\n" +
                "Ejemplo: \n");
        System.out.println();
        arbol.imprime();

        Arbol familia = new Arbol();
        System.out.println("-----------------------------------" +
                "\n\n[Introduzca los datos de los miembros de familia a visualizar,\n" +
                "empiece por el miembro mas mayor que hara de raiz del arbol]");
        System.out.println("Introduzca el nombre:");
        nombre = Entrada.cadena();
        System.out.println("Introduzca el genero (H o M):");
        genero = Entrada.caracter();
        System.out.println("Fecha de nacimiento:");
        fecha = Entrada.cadena();
        persona = new Miembro(nombre, null, genero, fecha);
        familia.addMiembro(persona); // aqui agregamos al miembro raiz

        familia.generarDescendientes(persona); //procedo a pedir infromación del resto de la familia

        System.out.println();
        familia.imprime(); //imprimo el arbol de la familia



    }



    public static class Miembro{
        String nombre;
        Miembro padre;              //id del padre. Si vacío se trata del miembro raíz
        char genero;                //H o M
        String nacimiento;          //fecha de nacimento
        ArrayList<Miembro> hijos = new ArrayList<Miembro>();    //lista de hijos


        public Miembro(String nombre, Miembro padre, char genero, String nacimiento) {
            this.nombre = nombre;
            this.padre = padre;
            this.genero = genero;
            this.nacimiento = nacimiento;
        }


        public String getNombre() {
            return nombre;
        }


        public void addHijo(Miembro hijo){
            this.hijos.add(hijo);
        }

        public ArrayList<Miembro> getHijos(){
            return hijos;
        }

        public boolean tieneHijos(){
            if(hijos.isEmpty()==true){
                return false;
            }else{
                return true;
            }
        }


        @Override
        public String toString(){
            if (padre!=null){ //si no se trata del miembro raiz:
                return nombre + " [Sexo: " + genero + "; Fecha de nacimiento: " + nacimiento + "; hijo/a de: " + padre.getNombre() + "]";
            }else{  //miembro raiz
                return nombre + " [Sexo: " + genero + "; Fecha de nacimiento: " + nacimiento + "]";
            }

        }
    }

    public static class Arbol{
        ArrayList<Miembro> familia = new ArrayList<Miembro>();
        int gen=0; // lo usare como contador para saber en que generacion me encuentro, sobre todo para formatear la salida.

        public void addMiembro(Miembro x){
            familia.add(x);
        }

        public void imprime(){ //la primera llamada
            Miembro miembro = familia.get(0);
            System.out.println(miembro);
            if(miembro.tieneHijos()){
                ArrayList<Miembro> hijos = miembro.getHijos();
                gen++;
                for(Miembro hijo: hijos){
                   imprime(hijo);
                }
            }else{
                gen--;
            }
        }

        public void imprime(Miembro miembro){ //para llamadas recursivas, pasamos y analizamos los descendientes

            for(int i=0; i<gen; i++){
                System.out.print("---");
            }
            System.out.println(miembro);
            if(miembro.tieneHijos()){
                ArrayList<Miembro> hijos = miembro.getHijos();
                gen++;
                for(Miembro hijo: hijos){
                    imprime(hijo);
                    gen--;
                }
            }
        }

        public void generarDescendientes(Miembro miembro){

            String nombre;
            String fecha;
            char genero;
            int nhijos;

            Miembro padre = miembro;
            Miembro persona;

            System.out.println("Introduce el numero de hijos de " + padre.getNombre());
            nhijos = Entrada.entero();
            for(int j=1; j<=nhijos; j++){
                System.out.println("Introduzca el nombre del hijo/a "+j+" de "+ padre.getNombre());
                nombre = Entrada.cadena();
                System.out.println("Introduzca el genero (H o M):");
                genero = Entrada.caracter();
                System.out.println("Fecha de nacimiento:");
                fecha = Entrada.cadena();
                persona = new Miembro(nombre, padre, genero, fecha);
                padre.addHijo(persona);
                familia.add(persona);
                generarDescendientes(persona);

            }
        }
    }

}