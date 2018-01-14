/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moduloAlmacen;

import comparadores.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;
import main.Rutas;

/**
 *
 * @author Francisco Blázquez Matías (PB1)
 */
public class ModuloAlmacen {
    
    private static ArrayList<Producto> listaProductos = new ArrayList<>(); //Lo declaramos static porque se utilizará en otras clases y se podrá modificar desde ellas.
    private int numProductos;
    private final String[] productoAtributos = {"Codigo", "Descripcion", "Deporte", "Cantidad", "Precio sin Iva", "Precio del iva", "Precio Final"};
    private String ficheroAlmacen;
    private final String delimitador = ";";
    
    public void menuAlmacen(){
        
        Scanner scan = new Scanner(System.in);
        boolean excepcion = true;
        String opcion;
        int opcionInt = 0;
        
        do{
             
            System.out.println("==============================================================");
            System.out.println("\t\t\tMENU ALMACENES");
            System.out.println("==============================================================");
            System.out.println();
            System.out.println("1) Generar almacenes aleatorios.");
            System.out.println("2) Exportar almacenes a (*.txt).");
            System.out.println("3) Importar almacenes de (*.txt).");
            System.out.println("4) Listar almacen.");
            System.out.println();
            System.out.println("5) Volver al menu principal.");
            System.out.println();
            System.out.println();
            
            do{
            
                try{

                    System.out.print("Introduzca una opcion: ");
                    opcion = scan.nextLine();
                    opcionInt = Integer.parseInt(opcion);
                    excepcion = false;

                }
                catch(NumberFormatException e){

                    excepcion = true;
                    System.out.println("Has introducido un caracter no valido.");

                }

            }while(excepcion);
            
            switch(opcionInt){
                
                case 1:
                    almacenAleatorio();
                    break;
                    
                case 2:
                    exportarAlmacen();
                    break;
                    
                case 3:
                    importarAlmacen();
                    break;
                    
                case 4:
                    mostrarAlmacen();
                    break;
                    
                case 5:
                    System.out.println();
                    System.out.println("Saliendo al menu principal..");
                    System.out.println();
                    break;
                    
                default:
                    System.out.println();
                    System.out.println("Has introducido una opcion incorrecta, vuelve a intentarlo.");
                    break;
                    
            }
            

        }while(opcionInt != 5);
        
    }
    
    /**
     * Genera aleatoriamente productos para el almacen.
     */
    public void almacenAleatorio(){
        
        Random rand = new Random(System.nanoTime());
        String codigo = "";
        String precioSinIva = "";
        float precioConIva;
        
        /*Limpiamos el almacen por si tiene algo*/
        listaProductos.clear();
        
        /*Creamos el numero de productos que va a haber aleatoriamente pero siempre habiendo
        como minimo un producto*/
        numProductos = rand.nextInt(50)+1;
        
        for(int i=0; i<numProductos; i++){
            
            /*Creamos los objetos*/
            Producto p = new Producto();
            ProductoAleatorio pa = new ProductoAleatorio();
            
            /*Damos un codigo al producto*/
            for(int j=0; j<6; j++){
                
                codigo = codigo + Integer.toString(rand.nextInt(9));
                
            }
            p.setCodNum(codigo);
            codigo = "";
            
            /*Damos una descripcion al producto con una descripcion aleatoria y una palabra aleatoria*/
            p.setDescripcion(pa.campoAleatorio(pa.getDescripcion()));
            p.setMarca(pa.campoAleatorio(pa.getPalabraInutil()));
            
            /*Damos un deporte al producto, destinatario, talla y color al producto*/
            p.setDeporte(pa.campoAleatorio(pa.getDeporte()));
            p.setDestinatario(pa.campoAleatorio(pa.getDestinatario()));
            p.setColor(pa.campoAleatorio(pa.getColor()));
            p.setTalla(pa.campoAleatorio(pa.getTalla()));
            
            /*Damos una cantidad de este producto al almacen*/
            p.setCantidad(Integer.toString(rand.nextInt(25) + 1));
            
            /*Damos el precio sin iva generando dos numeros aleatorios(la parte entera y la parte decimal)*/
            precioSinIva = Integer.toString(rand.nextInt(30)) + "." + Integer.toString(rand.nextInt(99));
            p.setPrecioSinIva(precioSinIva);
            
            /*Ponemos el precio del iva para ese producto y el precio final (con iva)*/
            precioConIva = (Float.parseFloat(precioSinIva) * Float.parseFloat(p.getIva())) / 100;
            p.setPrecioIva(Float.toString(precioConIva));
            precioConIva = precioConIva + Float.parseFloat(precioSinIva);
            p.setPrecioConIva(Float.toString(precioConIva));
            
            /*Añadimos el objeto al arrayList*/
            listaProductos.add(p);
            
        }
        
        
    }
    
    /**
     * Genera un archivo "*.csv" al que se guardara el almacen.
     */
    public void exportarAlmacen(){
            
        if(!listaProductos.isEmpty()){
            
            Rutas r = new Rutas();
            Scanner scan = new Scanner(System.in);
            
            System.out.print("Introduzca el nombre del fichero que contiene la copia de seguridad del almacen: ");
            ficheroAlmacen = scan.nextLine();
            
            /*Enlazamos las rutas*/
            Path p = Rutas.pathToFileOnFolderOnFolderOnDesktop(ficheroAlmacen + ".txt", r.getCarpetaAlmacen(), r.getNombreCarpeta());
            File fichero = new File(p.toString());

            /*Si no existe el fichero lo creamos*/
            if(!fichero.exists()){
                try{

                    fichero.createNewFile();

                }
                catch(IOException e){
                    System.out.println("Error en la creacion del fichero.");
                }
            }

            try{
                /*Creamos un objeto para escribir en la ruta que le pasamos al constructor*/
                PrintWriter pw = new PrintWriter(new File(p.toString()));
                /*Recorremos el arrayList escribiendo todos los objetos*/
                for(int i=0; i<listaProductos.size(); i++){

                    pw.printf("%25s%25s%25s%25s%25s%25s%25s%25s%25s%25s%25s", listaProductos.get(i).getCodNum() + delimitador
                                , listaProductos.get(i).getDescripcion() + delimitador, listaProductos.get(i).getMarca() + delimitador
                                , listaProductos.get(i).getDestinatario() + delimitador
                                , listaProductos.get(i).getColor() + delimitador, listaProductos.get(i).getTalla() + delimitador
                                , listaProductos.get(i).getDeporte() + delimitador, listaProductos.get(i).getCantidad() + delimitador, listaProductos.get(i).getPrecioSinIva() + delimitador
                                , listaProductos.get(i).getPrecioIva() + delimitador, listaProductos.get(i).getPrecioConIva() + delimitador);
                    pw.println();

                }
                pw.close();

            }
            catch(FileNotFoundException e){
                System.out.println("Error en la lectura del fichero.");
            }
        }
        else{
            /*Si el almacen esta vacio, lo indicamos.*/
            System.out.println("No existe ningun elemento en el almacen.");
            
        }
    }
    
    /**
     * Importa desde un archivo "*.csv" un almacen.
     */
    public void importarAlmacen(){
        
        Rutas r = new Rutas();
        String lectura;
        Scanner scan = new Scanner(System.in);
        
        /*Pedimos el archivo a importar*/
        System.out.print("Introduzca el nombre del archivo que contiene el almacen a importar: ");
        ficheroAlmacen = scan.nextLine();
        
        /*Establecemos la ruta*/
        Path p = Rutas.pathToFileOnFolderOnFolderOnDesktop(ficheroAlmacen + ".txt", r.getCarpetaAlmacen(), r.getNombreCarpeta());

        if((p.toFile().exists()) && (p.toFile().isFile())){
        
            try{
                /*Creamos un scanner del archivo*/
                Scanner scanL = new Scanner(new File(p.toString()));
                /*Limpiamos la lista por si tiene algo*/
                listaProductos.clear();

                /*Leemos el archivo hasta que no haya mas lineas*/
                while(scanL.hasNextLine()){

                    lectura = scanL.nextLine();

                    /*Metemos en el vector cada uno de los campos de una linea*/
                    String[] vector = lectura.split(delimitador);
                    Producto pr = new Producto();

                    /*Recorremos el vector y vamos quitando el tabulado para dejar los campos sin tabulados*/
                    for(int i=0; i<vector.length; i++){

                        /*Reemplazamos el espacio por nada*/
                        vector[i] = vector[i].replaceAll(" ","");

                    }

                    /*Metemos cada campo de vector en su correspondiente del objeto Producto*/
                    pr.setCodNum(vector[0]);
                    pr.setDescripcion(vector[1]);
                    pr.setMarca(vector[2]);
                    pr.setDestinatario(vector[3]);
                    pr.setColor(vector[4]);
                    pr.setTalla(vector[5]);
                    pr.setDeporte(vector[6]);
                    pr.setCantidad(vector[7]);
                    pr.setPrecioSinIva(vector[8]);
                    pr.setPrecioIva(vector[9]);
                    pr.setPrecioConIva(vector[10]);

                    /*Metemos el objeto en el arrayList*/
                    listaProductos.add(pr);

                }
                scanL.close();

            }
            catch(FileNotFoundException e){
                System.out.println("No se puede leer el archivo");
            }
        }
        else{
            
            System.out.println("El archivo no existe.");
            
        }
    }
    
    /**
     * Muestra todos los productos del almacen encolumnados.
     */
    public void mostrarAlmacen(){

        /*Si la lista esta vacia ponemos error*/
        if(listaProductos.isEmpty()){
            
            System.out.println("No existe ningun producto en el almacen.");
            
        }
        else{
            
            ordenarAlmacen();
            
            /*Ponemos el nombre de los atributos*/
            System.out.printf("%11s", productoAtributos[0]);
            System.out.printf("%36s", productoAtributos[1]);
            System.out.printf("%31s", productoAtributos[2]);
            System.out.printf("%29s", productoAtributos[3]);
            System.out.printf("%26s", productoAtributos[4]);
            System.out.printf("%26s", productoAtributos[5]);
            System.out.printf("%26s", productoAtributos[6]);
            
            
            System.out.println();
            for(int i = 0; i<190; i++){
                System.out.printf("-");
            }
            System.out.println();
            /*Ponemos los campos de cada producto*/
            for(int i = 0; i<listaProductos.size(); i++){

                System.out.printf("%10s |%42s |%25s |%25s |%25s |%25s |%25s", listaProductos.get(i).getCodNum()
                        , listaProductos.get(i).getDescripcion() + " " + listaProductos.get(i).getMarca() + " " + listaProductos.get(i).getDestinatario() +
                                " " + listaProductos.get(i).getColor() + " " + listaProductos.get(i).getTalla()
                        , listaProductos.get(i).getDeporte(), listaProductos.get(i).getCantidad(), listaProductos.get(i).getPrecioSinIva()
                        , listaProductos.get(i).getPrecioIva(), listaProductos.get(i).getPrecioConIva());
                
                System.out.println();
                
            }
            System.out.println();
            
        }
    }
    
    /**
     * Ordena la salida de datos por pantalla mediante el campo especificado
     */
    public void ordenarAlmacen(){
        
        String campo;
        Scanner scan = new Scanner(System.in);
        boolean control = true;
        
        do{            
            
            for(int i = 0; i < productoAtributos.length; i++) {
                
                System.out.println(productoAtributos[i]);
                
            }
            System.out.println("Marca");
            System.out.println("Destinatario");
            System.out.println("Color");
            System.out.println("Talla");
            System.out.println();
            System.out.print("Introduzca el campo por el que ordenar el almacen: ");
            campo = scan.nextLine();
            campo = campo.toLowerCase();
            
            switch(campo){
                
                case "codigo":
                    Collections.sort(listaProductos, new ComparadorPorCodigo());
                    control = false;
                    break;
                    
                case "descripcion":
                    Collections.sort(listaProductos, new ComparadorPorDescripcion());
                    control = false;
                    break;
                    
                case "marca":
                    Collections.sort(listaProductos, new ComparadorPorMarca());
                    control = false;
                    break;
                    
                case "deporte":
                    Collections.sort(listaProductos, new ComparadorPorDeporte());
                    control = false;
                    break;
                    
                case "destinatario":
                    Collections.sort(listaProductos, new ComparadorPorDestinatario());
                    control = false;
                    break;
                    
                case "color":
                    Collections.sort(listaProductos, new ComparadorPorColor());
                    control = false;
                    break;
                    
                case "talla":
                    Collections.sort(listaProductos, new ComparadorPorTalla());
                    control = false;
                    break;
                    
                case "cantidad":
                    Collections.sort(listaProductos, new ComparadorPorCantidad());
                    control = false;
                    break;
                    
                case "precio sin iva":
                    Collections.sort(listaProductos, new ComparadorPorPrecioSinIva());
                    control = false;
                    break;
                    
                case "precio del iva":
                    Collections.sort(listaProductos, new ComparadorPorPrecioDelIva());
                    control = false;
                    break;
                    
                case "precio final":
                    Collections.sort(listaProductos, new ComparadorPorPrecioConIva());
                    control = false;
                    break;
                    
                default:
                    control = true;
                    break;
                
            }
            
        }while(control);
        
    }
    
    /*METODOS SETTER AND GETTER*/

    /**
     * 
     * @return ArrayList con los productos
     */
    public ArrayList<Producto> getListaProductos() {
        return listaProductos;
    }

    /**
     * 
     * @param listaProductos 
     */
    public void setListaProductos(ArrayList<Producto> listaProductos) {
        this.listaProductos = listaProductos;
    }

    /**
     * 
     * @return Vector con los atributos del producto
     */
    public String[] getProductoAtributos() {
        return productoAtributos;
    }
    
}
