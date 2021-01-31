import java.util.*;

public class Main{

    public static void main(String[] args){

        Miembro abuelo = new Miembro (0, "Richard", null,'H');
        Miembro padre = new Miembro (1, "Jacek", abuelo, 'H');
        Miembro tia = new Miembro (2, "Monika", abuelo, 'M');
        Miembro hijo = new Miembro (3, "Maciej", padre, 'H');
        abuelo.addHijo(padre);
        abuelo.addHijo(tia);
        padre.addHijo(hijo);

        Arbol arbol = new Arbol();

        arbol.addMiembro(abuelo);
        arbol.addMiembro(padre);
        arbol.addMiembro(tia);
        arbol.addMiembro(hijo);

        arbol.imprime();

    }



    public static class Miembro{
        Integer id;                 //cada miembro tendrá un unico id
        String nombre;
        Miembro padre;              //id del padre. Si vacío se trata del miembro raíz
        char genero;                //H / M
        ArrayList<Miembro> hijos = new ArrayList<Miembro>();    //lista de hijos


        public Miembro(Integer id, String nombre, Miembro padre, char genero) {
            this.id = id;
            this.nombre = nombre;
            this.padre = padre;
            this.genero = genero;
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
                return " " + nombre + " [" + genero + "]  Hijo/a de: " + padre.getNombre();
            }else{  //miembro raiz
                return "" + nombre + " [" + genero + "]";
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
            System.out.println("Generacion 0  " + miembro);
            if(miembro.tieneHijos()){
                gen++;
                ArrayList<Miembro> hijos = miembro.getHijos();
                for(Miembro hijo: hijos){
                    imprime(hijo);
                }
            }
        }

        public void imprime(Miembro miembro){ //para llamadas recursivas, pasamos y analizamos los descendientes

            System.out.println("Generacion: "+ gen + "  " + miembro);
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