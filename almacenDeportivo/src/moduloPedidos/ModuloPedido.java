/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moduloPedidos;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;
import main.Rutas;
import moduloAlmacen.ModuloAlmacen;
import moduloAlmacen.Producto;
import moduloClientes.ModuloCliente;

/**
 *
 * @author Francisco Blázquez Matías (PB1)
 */
public class ModuloPedido {
    
    private ArrayList<Pedido> listaPedidos = new ArrayList<>();
    private final String delimitador = ";";
    
    public void menuPedidos(){
        
        Scanner scan = new Scanner(System.in);
        boolean excepcion = true;
        String opcion;
        int opcionInt = 0;
        
        do{
             
            System.out.println("==============================================================");
            System.out.println("\t\t\tMENU PEDIDOS");
            System.out.println("==============================================================");
            System.out.println();
            System.out.println("1) Realizar pedidos.");
            System.out.println("2) Anular pedidos.");
            System.out.println("3) Generar ficheros de pedidos del día (*.csv).");
            System.out.println("4) Generar facturas de pedidos del día (*.txt).");
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
                    realizarPedido();
                    break;
                    
                case 2:
                    anularPedido();
                    break;
                    
                case 3:
                    generarFicheroPedidos();
                    break;
                    
                case 4:
                    generarFicheroFacturas();
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
     * Realiza los pedidos modificando los articulos del almacen.
     */
    public void realizarPedido(){
        
        boolean salida = true;
        boolean encontrado = false;
        String mail = "";
        String cod, cant;
        String campo;
        Scanner scan = new Scanner(System.in);
        ModuloCliente c = new ModuloCliente();
        ModuloAlmacen vacio = new ModuloAlmacen();

        if(!c.getListaClientes().isEmpty()){
            
            c.listarMailClientes();
            System.out.print("Introduzca el mail del cliente que quiere ejecutar el pedido: ");
            mail = scan.nextLine();

            /*Recorremos el arrayList de los clientes y comparamos hasta encontrar el que corresponda con el mail introducido*/
            for(int i=0; i<c.getListaClientes().size(); i++){

                if(mail.equals(c.getListaClientes().get(i).getMail())){

                    /*Confirmamos que se ha encontrado*/
                    encontrado = true;
                    break;

                }
            }
        }
        /*Si se ha encontrado el cliente se procede a hacer el pedido*/
        if(encontrado){

            if(!vacio.getListaProductos().isEmpty()){
                
                Pedido ped = new Pedido(mail);
                do{

                    ModuloAlmacen MA = new ModuloAlmacen();
                    /*Mostramos los productos del almacen*/
                    MA.mostrarAlmacen();

                    /*Pedimos el codigo del articulo*/
                    System.out.print("Introduzca el codigo del articulo que quiere comprar: ");
                    cod = scan.nextLine();

                    for(int i=0; i<MA.getListaProductos().size(); i++){
                        /*Recorremos el arrayList de los productos buscando el que tiene el mismo codigo que el introducido*/
                        if(cod.equals(MA.getListaProductos().get(i).getCodNum())){
                            /*Metemos el producto con el codigo en el arrayList de los productos pedidos.*/
                            //ped.getProductoPedido().add(MA.getListaProductos().get(i));
                            do{
                                /*Pedimos la cantidad de ese producto que quiere pedir*/
                                System.out.print("Introduzca la cantidad que quiere pedir: ");
                                cant = scan.nextLine();

                            }while((Integer.parseInt(cant)) > (Integer.parseInt(MA.getListaProductos().get(i).getCantidad())));
                            /*Creamos un producto nuevo con los mismos datos que el producto del almacen pero cambiando la cantidad*/
                            Producto pro = new Producto();
                            pro.setCantidad(cant);
                            pro.setCodNum(cod);
                            pro.setColor(MA.getListaProductos().get(i).getColor());
                            pro.setDeporte(MA.getListaProductos().get(i).getDeporte());
                            pro.setDescripcion(MA.getListaProductos().get(i).getDescripcion());
                            pro.setDestinatario(MA.getListaProductos().get(i).getDestinatario());
                            pro.setMarca(MA.getListaProductos().get(i).getMarca());
                            pro.setPrecioConIva(MA.getListaProductos().get(i).getPrecioConIva());
                            pro.setPrecioIva(MA.getListaProductos().get(i).getPrecioIva());
                            pro.setPrecioSinIva(MA.getListaProductos().get(i).getPrecioSinIva());
                            pro.setTalla(MA.getListaProductos().get(i).getTalla());

                            /*Añadimos el producto a la lista*/
                            ped.getProductoPedido().add(pro);
                            /*Recorremos el arrayList de los productos pedidos para dar con el que tiene el codigo que se ha introducido antes
                            y cambiarle la cantidad para que indique la cantidad de productos de ese tipo pedidos*/
                            for(int j=0; j<ped.getProductoPedido().size(); j++){

                                if(cod.equals(ped.getProductoPedido().get(j).getCodNum())){
                                    /*Fijamos la cantidad al producto pedido*/
                                    ped.getProductoPedido().get(j).setCantidad(cant);
                                    /*Restamos la cantidad del producto pedido a la cantidad del producto del almacen*/
                                    MA.getListaProductos().get(i).setCantidad(
                                            Integer.toString(
                                                    Integer.parseInt(MA.getListaProductos().get(i).getCantidad()) 
                                                    - Integer.parseInt(ped.getProductoPedido().get(j).getCantidad())));
                                    break;
                                }

                            }
                            break;

                        }

                    }
                    /*Salida de realizar pedidos*/
                    System.out.print("\nDesea realizar otro pedido?(si/no): ");
                    /*Si escribe un "no" salimos del do-while y por tanto de realizar pedidos*/
                    if(scan.nextLine().toLowerCase().equals("no")){
                        salida = false;
                    }

                }while(salida);
                listaPedidos.add(ped);
            }
            else{
                System.out.println("El almacen esta vacio");
            }
        }
        else{
            System.out.println("No se ha encontrado el cliente.");
        }
    }

    /**
     * Anula un pedido realizado.
     */
    public void anularPedido(){
        
        boolean salida = true;
        Scanner scan = new Scanner(System.in);
        
        String codigoPed;
        
        do{
            /*Se muestran todos los pedidos realizados*/
            mostrarListaPedidos();
            
            /*Pedimos el codigo del pedido a anular*/
            System.out.print("Escribe el codigo del pedido a anular: ");
            codigoPed = scan.nextLine();
            
            /*Recorremos el ArrayList buscando el pedido que tiene ese codigo*/
            for(int i=0; i<listaPedidos.size(); i++){
                
                if(codigoPed.equals(listaPedidos.get(i).getCodPed())){
                    /*Devolvemos los productos pedidos al almacen*/
                    for(int j=0; j<listaPedidos.get(i).getProductoPedido().size(); j++){
                        
                        ModuloAlmacen MA = new ModuloAlmacen();
                        for(int k=0; k<MA.getListaProductos().size(); k++){
                            /*Si coinciden los codigos del producto del almacen y del producto pedido devolvemos la cantidad*/
                            if(listaPedidos.get(i).getProductoPedido().get(j).getCodNum().equals(MA.getListaProductos().get(k).getCodNum())){
                                
                                MA.getListaProductos().get(k).setCantidad(Integer.toString(
                                        Integer.parseInt(MA.getListaProductos().get(k).getCantidad()) +
                                        Integer.parseInt(listaPedidos.get(i).getProductoPedido().get(j).getCantidad())));
                                break;
                                
                            }
                        }
                    }
                    /*Eliminamos del arrayList ese pedido*/
                    listaPedidos.remove(i);
                    break;
                }
                
            }
            
            /*Se pide si se desea seguir anulando pedidos, sino se sale de la opcion*/
            System.out.print("\nDesea anular otro pedido?(si/no): ");
            /*Si escribe un "no" salimos del do-while y por tanto de realizar pedidos*/
            if(scan.nextLine().toLowerCase().equals("no")){
                salida = false;
            }
        }while(salida);
        
    }
    
    /**
     * Genera el fichero de pedidos del dia.
     */
    public void generarFicheroPedidos(){
        
        Rutas R = new Rutas();
        String nombreFichero = "";
        Scanner scan = new Scanner(System.in);
        
        if(!listaPedidos.isEmpty()){
        
            for(int i=0; i<listaPedidos.size(); i++){

                nombreFichero = listaPedidos.get(i).getCodPed() +"_"+ listaPedidos.get(i).getMailCli() +"_"+ listaPedidos.get(i).getFecha() + ".csv";

                Path p = Rutas.pathToFileOnFolderOnFolderOnDesktop(nombreFichero, R.getCarpetaPedidos(), R.getNombreCarpeta());
                File fichero = new File(p.toString());

                /*Si no existe creamos el fichero*/
                if(!fichero.exists()){
                    try{
                        fichero.createNewFile();
                    }
                    catch(IOException e){
                        System.out.println("Error en la creacion del fichero");
                    }
                }

                try{

                    PrintWriter pw = new PrintWriter(new File(p.toString()));
                    for(int j=0; j<listaPedidos.get(i).getProductoPedido().size(); j++){
                        
                        pw.println(listaPedidos.get(i).getProductoPedido().get(j).getCodNum() + delimitador +
                                listaPedidos.get(i).getProductoPedido().get(j).getDescripcion() + delimitador +
                                listaPedidos.get(i).getProductoPedido().get(j).getMarca() + delimitador +
                                listaPedidos.get(i).getProductoPedido().get(j).getDeporte() + delimitador +
                                listaPedidos.get(i).getProductoPedido().get(j).getDestinatario() + delimitador +
                                listaPedidos.get(i).getProductoPedido().get(j).getColor() + delimitador +
                                listaPedidos.get(i).getProductoPedido().get(j).getTalla() + delimitador +
                                listaPedidos.get(i).getProductoPedido().get(j).getCantidad() + delimitador +
                                listaPedidos.get(i).getProductoPedido().get(j).getPrecioSinIva() + delimitador +
                                listaPedidos.get(i).getProductoPedido().get(j).getPrecioIva() + delimitador +
                                listaPedidos.get(i).getProductoPedido().get(j).getPrecioConIva() + delimitador);
                        
                    }
                    pw.close();

                }
                catch (FileNotFoundException e){
                    System.out.println("No se puede leer el fichero.");
                }

            }
        }
        else{
            
            System.out.println("No existen pedidos.");
            
        }
    }

    /**
     * Genera las facturas correspondientes a los pedidos del dia.
     */
    public void generarFicheroFacturas(){
        
        Rutas R = new Rutas();
        Scanner scan = new Scanner(System.in);
        ModuloAlmacen MA = new ModuloAlmacen();
        String dia, mes, year, fecha;
        String nombreFichero;
        float sumaBase = 0;
        float sumaIva = 0;
        float sumaTot = 0;
        
        /*Pedimos la fecha de la factura*/
        System.out.println("Introduzca la fecha de la factura: ");
        System.out.print("Dia: ");
        dia = scan.nextLine();
        System.out.print("Mes: ");
        mes = scan.nextLine();
        System.out.print("Año: ");
        year = scan.nextLine();
        /*Si escriben una sola cifra le añadimos el cero delante*/
        if(dia.length() < 2){
            
            dia = "0" + dia;
            
        }
        if(mes.length() < 2){
            
            mes = "0" + mes;
            
        }
        /*Juntamos la fecha en un solo formato*/
        fecha = dia + "-" + mes + "-" + year;
        
        if(!listaPedidos.isEmpty()){
            /*Hacemos las facturas de los pedidos que estan en la memoria*/
            for(int i=0; i<listaPedidos.size(); i++){
                /*Para todas las facturas que tengan la fecha que hemos escrito*/
                if(listaPedidos.get(i).getFecha().equals(fecha)){
                    /*Creamos una nueva factura pasandole el codigo de ese pedido y el correo*/
                    Factura F1 = new Factura(listaPedidos.get(i).getCodPed(), listaPedidos.get(i).getMailCli());
                    /*Para esa factura ponemos hacemos la suma de todos los productos para cada precio*/
                    for(int j=0; j<listaPedidos.get(i).getProductoPedido().size(); j++){

                        sumaBase = sumaBase + (Float.parseFloat(listaPedidos.get(i).getProductoPedido().get(j).getPrecioSinIva()) * Float.parseFloat(listaPedidos.get(i).getProductoPedido().get(j).getCantidad()));
                        sumaIva = sumaIva + (Float.parseFloat(listaPedidos.get(i).getProductoPedido().get(j).getPrecioIva()) * Float.parseFloat(listaPedidos.get(i).getProductoPedido().get(j).getCantidad()));
                        sumaTot = sumaTot + (Float.parseFloat(listaPedidos.get(i).getProductoPedido().get(j).getPrecioConIva()) * Float.parseFloat(listaPedidos.get(i).getProductoPedido().get(j).getCantidad()));

                    }

                    /*Los añadimos a la factura*/
                    F1.setTotalBase(Float.toString(sumaBase));
                    F1.setTotalIva(Float.toString(sumaIva));
                    F1.setTotal(Float.toString(sumaTot));
                    
                    /*Restauramos el valor de las variables suma*/
                    sumaBase = 0;
                    sumaIva = 0;
                    sumaTot = 0;
                    /*Creamos el nombre del archivo (codigoFactura_correoCliente)*/
                    nombreFichero = F1.getCodPed()+ "_" + F1.getMailCli() + ".txt";
                    /*Establecemos la ruta dentro de la carpeta de pedidos*/
                    Path pWM = Rutas.pathToFileOnFolderOnFolderOnDesktop(nombreFichero, R.getCarpetaPedidos(), R.getNombreCarpeta());
                    File fWM = new File(pWM.toString());
                    /*Si no existe el archivo*/
                    if(!fWM.exists()){
                        try{
                            /*Creamos el archivo*/
                            fWM.createNewFile();
                            try {
                                
                                PrintWriter pw = new PrintWriter(fWM.getAbsoluteFile());
                                pw.println("Nº de Factura: " + F1.getCodFact());
                                pw.println("Pedido nº: " + F1.getCodPed());
                                pw.println("Fecha: " + fecha);
                                pw.println();
                                pw.println();
                                pw.printf("%s", MA.getProductoAtributos()[0]);
                                pw.printf("%30s", MA.getProductoAtributos()[1]);
                                pw.printf("%27s", MA.getProductoAtributos()[2]);
                                pw.printf("%25s", MA.getProductoAtributos()[3]);
                                pw.printf("%25s", MA.getProductoAtributos()[4]);
                                pw.printf("%25s", MA.getProductoAtributos()[6]);
                                pw.println();
                                pw.println();
                                
                                for(int j=0; j<listaPedidos.get(i).getProductoPedido().size(); j++){
                                    
                                    pw.printf("%s %35s %20s %20s %25s %25s", listaPedidos.get(i).getProductoPedido().get(j).getCodNum(),
                                                    listaPedidos.get(i).getProductoPedido().get(j).getDescripcion() + " " +
                                                    listaPedidos.get(i).getProductoPedido().get(j).getMarca() + " " +
                                                    listaPedidos.get(i).getProductoPedido().get(j).getDestinatario() + " " +
                                                    listaPedidos.get(i).getProductoPedido().get(j).getColor() + " " +
                                                    listaPedidos.get(i).getProductoPedido().get(j).getTalla(),
                                                    listaPedidos.get(i).getProductoPedido().get(j).getDeporte(),
                                                    listaPedidos.get(i).getProductoPedido().get(j).getCantidad(),
                                                    Float.toString(Float.parseFloat(listaPedidos.get(i).getProductoPedido().get(j).getPrecioSinIva()) * Float.parseFloat(listaPedidos.get(i).getProductoPedido().get(j).getCantidad())),
                                                    Float.toString(Float.parseFloat(listaPedidos.get(i).getProductoPedido().get(j).getPrecioConIva()) * Float.parseFloat(listaPedidos.get(i).getProductoPedido().get(j).getCantidad())));
                                    pw.println();
                                    
                                }
                                
                                pw.println("Total Base: " + F1.getTotalBase());
                                pw.println("Total Iva: " + F1.getTotalIva());
                                pw.println("Total Factura: " + F1.getTotal());
                                
                                pw.close();
                            }
                            catch(FileNotFoundException e){
                                System.out.println("Error en la lectura del archivo.");
                            }
                            
                        }
                        catch(IOException e){
                            System.out.println("Error en la creacion del archivo.");
                        }
                    }
                }
            }
        }
        
        /*Establecemos la ruta a la carpeta de los pedidos*/
        Path p = Rutas.pathToFileOnFolderOnDesktop(R.getCarpetaPedidos(), R.getNombreCarpeta());
        
        /*Creamos un vector de File con esa ruta para listar los archivos "*.csv"*/
        File[] contenido = p.toFile().listFiles();
        
        for(File fichero : contenido){
            
            if(fichero.isFile()&&(fichero.getName().contains(".csv"))){
                /*Si el fichero contiene la fecha en el nombre y son pedidos(no facturas que pueda haber)*/
                if(fichero.getName().contains(fecha)){
                    /*Separamos el nombre del archivo en 3 partes en las cuales estaran el codigo del pedido y el mail del cliente
                    en las dos primeras respectivamente*/
                    String[] vNombre = fichero.getName().split("_");
                    
                    Factura F2 = new Factura(vNombre[0], vNombre[1]);
                    nombreFichero = vNombre[0] + "_" + vNombre[1] + ".txt";

                    /*Creamos un arrayList temporal donde meter los productos*/
                    ArrayList<Producto> temp = new ArrayList<>();
                    /*Creamos la lectura del archivo*/
                    try{
                        
                        Scanner sFich = new Scanner(fichero.getAbsoluteFile());
                        /*Leemos hasta que finalice el archivo*/
                        while(sFich.hasNextLine()){

                            String lectura = sFich.nextLine();
                            /*Separamos los campos de la linea leida y la metemos en un vector*/
                            String[] vCampos = lectura.split(delimitador);
                            /*Creamos un objeto producto y rellenamos sus campos con lo que hay en el vector*/
                            Producto P = new Producto();
                            P.setCodNum(vCampos[0]);
                            P.setDescripcion(vCampos[1]);
                            P.setMarca(vCampos[2]);
                            P.setDestinatario(vCampos[4]);
                            P.setColor(vCampos[5]);
                            P.setTalla(vCampos[6]);
                            P.setDeporte(vCampos[3]);
                            P.setCantidad(vCampos[7]);
                            P.setPrecioSinIva(vCampos[8]);
                            P.setPrecioIva(vCampos[9]);
                            P.setPrecioConIva(vCampos[10]);

                            /*Metemos el producto en el arrayList temporal*/
                            temp.add(P);
                        }
                    }
                    catch(FileNotFoundException e){
                        System.out.println("Error en la lectura del archivo");
                    }
                    
                    for(int i=0; i<temp.size(); i++){
                        
                        sumaBase = sumaBase + (Float.parseFloat(temp.get(i).getPrecioSinIva()) * Float.parseFloat(temp.get(i).getCantidad()));
                        sumaIva = sumaIva + (Float.parseFloat(temp.get(i).getPrecioIva()) * Float.parseFloat(temp.get(i).getCantidad()));
                        sumaTot = sumaTot + (Float.parseFloat(temp.get(i).getPrecioConIva()) * Float.parseFloat(temp.get(i).getCantidad()));

                    }
                    /*Rellenamos la factura con las sumas de los precios.*/
                    F2.setTotalBase(Float.toString(sumaBase));
                    F2.setTotalIva(Float.toString(sumaIva));
                    F2.setTotal(Float.toString(sumaTot));
                    
                    /*Restauramos el valor de las variables suma*/
                    sumaBase = 0;
                    sumaIva = 0;
                    sumaTot = 0;
                    
                    /*Establecemos la ruta dentro del directorio de pedidos*/
                    Path pWC = Rutas.pathToFileOnFolderOnFolderOnDesktop(nombreFichero, R.getCarpetaPedidos(), R.getNombreCarpeta());
                    File fWC = new File(pWC.toString());
                    
                    /*Si no existe el archivo*/
                    if(!fWC.exists()){
                        
                        try{
                            /*Creamos el archivo*/
                            fWC.createNewFile();
                            try {
                                
                                PrintWriter pw = new PrintWriter(fWC.getAbsoluteFile());
                                pw.println("Nº de Factura: " + F2.getCodFact());
                                pw.println("Pedido nº: " + F2.getCodPed());
                                pw.println("Fecha: " + fecha);
                                pw.println();
                                pw.println();
                                pw.printf("%s", MA.getProductoAtributos()[0]);
                                pw.printf("%30s", MA.getProductoAtributos()[1]);
                                pw.printf("%27s", MA.getProductoAtributos()[2]);
                                pw.printf("%25s", MA.getProductoAtributos()[3]);
                                pw.printf("%25s", MA.getProductoAtributos()[4]);
                                pw.printf("%25s", MA.getProductoAtributos()[6]);
                                pw.println();
                                pw.println();
                                
                                for(int i=0; i<temp.size(); i++){
                                    
                                    pw.printf("%s %35s %20s %20s %25s %25s", temp.get(i).getCodNum(),
                                                    temp.get(i).getDescripcion() + " " +
                                                    temp.get(i).getMarca() + " " +
                                                    temp.get(i).getDestinatario() + " " +
                                                    temp.get(i).getColor() + " " +
                                                    temp.get(i).getTalla(),
                                                    temp.get(i).getDeporte(),
                                                    temp.get(i).getCantidad(),
                                                    Float.toString(Float.parseFloat(temp.get(i).getPrecioSinIva()) * Float.parseFloat(temp.get(i).getCantidad())),
                                                    Float.toString(Float.parseFloat(temp.get(i).getPrecioConIva()) * Float.parseFloat(temp.get(i).getCantidad())));
                                    pw.println();
                                    
                                }
                                pw.println();
                                pw.println("Total Base: " + F2.getTotalBase());
                                pw.println("Total Iva: " + F2.getTotalIva());
                                pw.println("Total Factura: " + F2.getTotal());
                                
                                pw.close();
                            }
                            catch(FileNotFoundException e){
                                System.out.println("Error en la lectura del archivo.");
                            }
                            
                        }
                        catch(IOException e){
                            System.out.println("Error en la creacion del archivo.");
                        }
                    }
                }
            }
        }
    }
    
    /**
     * Muestra la lista de los pedidos.
     */
    public void mostrarListaPedidos(){
        
        ModuloAlmacen MA = new ModuloAlmacen();
        
        /*Si la lista esta vacia ponemos error*/
        if(listaPedidos.isEmpty()){
            
            System.out.println("No existe ningun producto en el almacen.");
            
        }
        else{
            
            System.out.println();
            /*Ponemos los campos de cada producto*/
            for(int i = 0; i<listaPedidos.size(); i++){

                System.out.println("Codigo del Pedido: " +listaPedidos.get(i).getCodPed());
                System.out.println("Fecha: " + listaPedidos.get(i).getFecha());
                System.out.println("Cliente: " + listaPedidos.get(i).getMailCli());
                
                System.out.printf("%11s", MA.getProductoAtributos()[0]);
                System.out.printf("%36s", MA.getProductoAtributos()[1]);
                System.out.printf("%31s", MA.getProductoAtributos()[2]);
                System.out.printf("%29s", MA.getProductoAtributos()[3]);
                System.out.printf("%26s", MA.getProductoAtributos()[6]);
                System.out.println();
                
                for(int j=0; j<138; j++){
                    System.out.printf("-");
                }
                System.out.println();
                
                for(int j=0; j<listaPedidos.get(i).getProductoPedido().size(); j++){
                    System.out.printf("%10s %42s %25s %25s %25s", listaPedidos.get(i).getProductoPedido().get(j).getCodNum()
                        , listaPedidos.get(i).getProductoPedido().get(j).getDescripcion() +
                                " " + listaPedidos.get(i).getProductoPedido().get(j).getMarca() +
                                " " + listaPedidos.get(i).getProductoPedido().get(j).getDestinatario() +
                                " " + listaPedidos.get(i).getProductoPedido().get(j).getColor() +
                                " " + listaPedidos.get(i).getProductoPedido().get(j).getTalla()
                        , listaPedidos.get(i).getProductoPedido().get(j).getDeporte()
                        , listaPedidos.get(i).getProductoPedido().get(j).getCantidad()
                        , listaPedidos.get(i).getProductoPedido().get(j).getPrecioConIva());
                    System.out.println();
                }
                System.out.println();
                System.out.println();
            }
            System.out.println();
            
        }
        
    }
    
}
