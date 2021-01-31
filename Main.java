import java.util.*;

public class Main{

    public static void main(String[] args){

        String nombre;
        String fecha;
        Miembro padre;


        //arbol de prueba
        Miembro m0 = new Miembro (0, "Ricardo", null,'H', "12-01-1945");
        Miembro m1 = new Miembro (1, "Jaime", m0, 'H', "23-07-1969");
        Miembro m2 = new Miembro (2, "Monica", m0, 'M', "16-08-1978");
        Miembro m3 = new Miembro (3, "Matias", m1, 'H', "01-12-1988");
        m0.addHijo(m1);
        m0.addHijo(m2);
        m1.addHijo(m3);

        Arbol arbol = new Arbol();

        arbol.addMiembro(m0);
        arbol.addMiembro(m1);
        arbol.addMiembro(m2);
        arbol.addMiembro(m3);

        System.out.print("\n+--- Arbol Genealogico ---+\n" +
                "Ejemplo: \n");
        System.out.println();
        arbol.imprime();

        System.out.println("\n\n[Introduzca los datos de los miembros de familia a visualizar,\n" +
                "empiece por el miembro mas mayor que hara de raiz del arbol]");
        System.out.println("Introduzca el nombre:");
        nombre = Entrada.cadena();
        System.out.println("Fecha de nacimiento (formato DD-MM-AAAA):");




    }



    public static class Miembro{
        Integer id;                 //cada miembro tendrá un unico id
        String nombre;
        Miembro padre;              //id del padre. Si vacío se trata del miembro raíz
        char genero;                //H o M
        String nacimiento;          //fecha de nacimento en formato DD-MM-AAAA
        ArrayList<Miembro> hijos = new ArrayList<Miembro>();    //lista de hijos


        public Miembro(Integer id, String nombre, Miembro padre, char genero, String nacimiento) {
            this.id = id;
            this.nombre = nombre;
            this.padre = padre;
            this.genero = genero;
            this.nacimiento = nacimiento;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getNombre() {
            return nombre;
        }

        public void setNombre(String nombre) {
            this.nombre = nombre;
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
                gen++;
                ArrayList<Miembro> hijos = miembro.getHijos();
                for(Miembro hijo: hijos){
                    imprime(hijo);
                }
            }
        }

        public void imprime(Miembro miembro){ //para llamadas recursivas, pasamos y analizamos los descendientes

            for(int i=0; i<gen; i++){
                System.out.print("---");
            }
            System.out.println(miembro);
            if(miembro.tieneHijos()){
                gen++;
                ArrayList<Miembro> hijos = miembro.getHijos();
                for(Miembro hijo: hijos){

                    imprime(hijo);
                }
            }else{
                gen--;
            }
        }
    }

}