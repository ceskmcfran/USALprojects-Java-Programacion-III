/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moduloClientes;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import main.Rutas;

/**
 *
 * @author Francisco Blázquez Matías (PB1)
 */
public class ModuloCliente {
    
    String[] clienteAtributos = {"Nombre", "Apellido1", "Apellido2", "E-Mail", "Password", "Direccion",
                                "Poblacion", "Provincia", "CP", "Telefono"};
    String ficheroSeguridad = "listadoClientes.bin";
    private int numClientes;
    
    /**
     * ArrayList del objeto "Cliente" que contendra los clientes de la app.
     */
    private static ArrayList<Cliente> listaClientes = new ArrayList<>();
    
    /**
     * Opcion "Modulo clientes" en del menu principal, contiene todo lo relativo a los clientes.
     */
    public void menuCliente(){
        
        Scanner scan = new Scanner(System.in);
        boolean excepcion = true;
        String opcion;
        int opcionInt = 0;
        
        do{
             
            System.out.println("==============================================================");
            System.out.println("\t\t\tMENU CLIENTES");
            System.out.println("==============================================================");
            System.out.println();
            System.out.println("1) Generar clientes aleatorios.");
            System.out.println("2) Copias de seguridad.");
            System.out.println("3) Listar clientes registrados.");
            System.out.println("4) Modificar clientes.");
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
                    clientesAleatorios();
                    break;
                    
                case 2:
                    menuCopiaSeguridad();
                    break;
                    
                case 3:
                    listarClientes();
                    break;
                    
                case 4:
                    menuModificarClientes();
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
     * Opcion que genera clientes aleatorios.
     */
    public void clientesAleatorios(){
        
        Random rand = new Random(System.nanoTime());
        Scanner scan = new Scanner(System.in);
        boolean excepcion = true;
        String temp;
        String telefono;
        String mail = "";
        String password = "";
        int aleatorio;
        
        /*Si la lista no esta vacia la vaciamos*/
        if(!listaClientes.isEmpty()){
            
            listaClientes.clear();
            
        }
        
        /*Pedimos el numero de clientes que va a haber entre 1 y 25*/
        do{
        
            try {

                System.out.print("Escriba el numero de clientes a crear: ");
                temp = scan.next();
                numClientes = Integer.parseInt(temp);
                excepcion = false;
                System.out.println();

            } catch (NumberFormatException e) {

                excepcion = true;
                System.out.println("Has introducido un caracter no valido.");

            }
            
        }while(excepcion || (numClientes <= 0) || (numClientes > 50));
        
        /*Recorremos todos los clientes que hemos dicho que iba a haber*/
        for(int i=0; i<numClientes; i++){
            
            Cliente cli = new Cliente(); //Creamos un objeto cliente nuevo
            ClienteAleatorio cliA = new ClienteAleatorio(); //Creamos un objeto aleatorio nuevo
            
            /*Damos nombre, apellidos, provincia y poblacion aleatorios al cliente*/
            cli.setNombre(cliA.campoAleatorio(cliA.getNombreA()));
            cli.setApellido1(cliA.campoAleatorio(cliA.getApellidosA()));
            cli.setApellido2(cliA.campoAleatorio(cliA.getApellidosA()));
            cli.setProvincia(cliA.campoAleatorio(cliA.getProvinciaA()));
            cli.setPoblacion(cli.getProvincia()); //La poblacion sera igual a la provincia
            
            /*Damos CP al cliente*/
            cli.setCp(Integer.toString(rand.nextInt(1000)+37000));
            
            /*Damos numero de telefono al cliente*/
            telefono = "6";
            for(int j=0; j<8; j++){
            
                telefono = telefono + Integer.toString(rand.nextInt(9));
                
            }
            cli.setTelefono(telefono);
            
            telefono = "";
            
            /*Damos direccion al cliente*/
            cli.setDireccion("C/" + cliA.campoAleatorio(cliA.getDireccionA()) + " " + Integer.toString(rand.nextInt(100)));
            
            /*Damos correo electronico al cliente*/
            mail = mail + cli.getNombre().substring(0, rand.nextInt(2)+2) 
                    + cli.getApellido1().substring(0, rand.nextInt(2)+1) 
                    + cli.getApellido2().substring(0, rand.nextInt(2)+1);
            cli.setMail(mail + "@" + cliA.campoAleatorio(cliA.getMailA()));
            
            mail = "";
            
            /*Damos contraseña al cliente*/
            for(int j=0; j<6; j++){
                
                aleatorio = rand.nextInt(25);
                password = password + cliA.getAbecedario().substring(aleatorio, aleatorio+1);
                
            }
            password = password + Integer.toString(rand.nextInt(100));
            cli.setPass(password);
            
            password = "";
            
            /*Añadimos el cliente a la lista*/
            listaClientes.add(cli);
            
        }
        
    }
    
    /**
     * Muestra por pantalla un listado de los clientes.
     */
    public void listarClientes(){
        /*Si la lista esta vacia ponemos error*/
        if(listaClientes.isEmpty()){
            
            System.out.println("No existe ningun cliente en la base de datos.");
            
        }
        else{
            /*Ponemos el nombre de los atributos*/
            for(int i=0; i<clienteAtributos.length; i++){

                System.out.printf("%25s ", clienteAtributos[i]);

            }

            System.out.println("\n");
            /*Ponemos los campos de cada cliente*/
            for(int i = 0; i<listaClientes.size(); i++){

                System.out.printf("%25s%25s%26s%29s%24s%26s%25s%25s%25s%29s", listaClientes.get(i).getNombre(), listaClientes.get(i).getApellido1()
                                                            , listaClientes.get(i).getApellido2(), listaClientes.get(i).getMail()
                                                            , listaClientes.get(i).getPass(), listaClientes.get(i).getDireccion()
                                                            , listaClientes.get(i).getPoblacion(), listaClientes.get(i).getProvincia()
                                                            , listaClientes.get(i).getCp(), listaClientes.get(i).getTelefono());
                System.out.println();

            }
            System.out.println();
            
        }
    }
    
    /**
     * Opcion "Copias de seguridad" del menu de clientes, contiene lo relativo a exportar e importar los datos de los clientes.
     */
    public void menuCopiaSeguridad(){
        
        String opcion;
        int opcionInt = 0;
        boolean excepcion = true;
        Scanner scan = new Scanner(System.in);
        Rutas R = new Rutas();
        
        do{
           
            System.out.println("==============================================================");
            System.out.println("\t\t\tMENU COPIA DE SEGURIDAD");
            System.out.println("==============================================================");
            System.out.println();
            System.out.println("1) Generar copia de seguridad.");
            System.out.println("2) Importar copia de seguridad.");
            System.out.println();
            System.out.println("3) Volver al menu de clientes.");
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
                    
                    System.out.println("Has introducido un caracter no valido.");
                    excepcion = true;
                    
                }

            }while(excepcion);
            
            switch(opcionInt){
                
                case 1:
                    generarCopiaClientes();
                    break;
                    
                case 2:
                    importarCopiaClientes();
                    break;
                
                case 3:
                    System.out.println();
                    System.out.println("Saliendo al menu clientes..");
                    System.out.println();
                    break;
                    
                default:
                    System.out.println();
                    System.out.println("Has introducido una opcion incorrecta.");
                    break;
                
            }
            
        }while(opcionInt != 3);
        
    }

    /**
     * Opcion "Generar copia de seguridad" del menu de "copia de seguridad", Guarda los clientes en un fichero con formato binario como mecanismo de seguridad.
     */
    public void generarCopiaClientes(){
        
        if(listaClientes.isEmpty()){
            System.out.println("La lista de los clientes esta vacia.");
        }
        else{
            
            /*Si la lista no esta vacia establecemos la ruta del archivo binario.*/
            Rutas r = new Rutas();
            Path p = Rutas.pathToFileOnFolderOnFolderOnDesktop(ficheroSeguridad, r.getCarpetaSeguridad(), r.getNombreCarpeta());
            ObjectOutputStream fSal = null;
            /*Si ya existe el archivo lo borramos para crear uno nuevo mas tarde.*/
            if(p.toFile().exists()){
                
                p.toFile().delete();
                
            }
            
            try {
                /*Creamos un flujo de entrada de datos a la ruta del archivo binario.*/
                fSal = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(p.toString())));
                
                for(int i=0; i<listaClientes.size(); i++){
                    
                    /*Escribimos todos los campos de cada cliente del arrayList en el archivo*/
                    fSal.writeObject(listaClientes.get(i).getMail());
                    fSal.writeObject(listaClientes.get(i).getPass());
                    fSal.writeObject(listaClientes.get(i).getNombre());
                    fSal.writeObject(listaClientes.get(i).getApellido1());
                    fSal.writeObject(listaClientes.get(i).getApellido2());
                    fSal.writeObject(listaClientes.get(i).getDireccion());
                    fSal.writeObject(listaClientes.get(i).getPoblacion());
                    fSal.writeObject(listaClientes.get(i).getProvincia());
                    fSal.writeObject(listaClientes.get(i).getCp());
                    fSal.writeObject(listaClientes.get(i).getTelefono());
                    
                
                }
                /*Cerramos el flujo de entrada.*/
                fSal.close();
                
            }catch(IOException ex) {
                System.out.println("Error al establecer el flujo de datos de entrada al fichero.");
            }
            
        }
    }
    
    /**
     * Opcion "Generar copia de seguridad" del menu de "copia de seguridad", Importa un fichero con formato binario que contendrá una copia de los clientes.
     */
    public void importarCopiaClientes(){
        
        /*Si no está vacia, vaciamos el listado de clientes para importar los nuevos clientes*/
        if(!listaClientes.isEmpty()){
            
            listaClientes.clear();
            
        }
        
        /*Una vez no haya elementos en el arrayList establecemos la ruta al fichero binario que contiene los clientes.*/
        Rutas r = new Rutas();
        Path p = Rutas.pathToFileOnFolderOnFolderOnDesktop(ficheroSeguridad, r.getCarpetaSeguridad(), r.getNombreCarpeta());
        ObjectInputStream fEnt = null;
        Cliente c = null;
        
        try {
            
            fEnt = new ObjectInputStream(new BufferedInputStream(new FileInputStream(p.toString())));
            while(true){

                /*Creamos un objeto cliente.*/
                c = new Cliente();
                
                try {
                    /*Metemos en el objeto los campos que lee en el archivo*/
                    c.setMail((String) fEnt.readObject());
                    c.setPass((String) fEnt.readObject());
                    c.setNombre((String) fEnt.readObject());
                    c.setApellido1((String) fEnt.readObject());
                    c.setApellido2((String) fEnt.readObject());
                    c.setDireccion((String) fEnt.readObject());
                    c.setPoblacion((String) fEnt.readObject());
                    c.setProvincia((String) fEnt.readObject());
                    c.setCp((String) fEnt.readObject());
                    c.setTelefono((String) fEnt.readObject());
                    
                } catch (IOException | ClassNotFoundException ex) {
                    fEnt.close();
                    break;
                    /*Cuando termine el fichero saltara la excepcion y lo que hara sera cerrar el fichero y salir del while.*/
                }
                
                /*Metemos el objeto al arrayList.*/
                listaClientes.add(c);

            }
            
        } catch (IOException ex) {
            System.out.println("Error al establecer el flujo de datos de salida al fichero.");
        }
    }
    
    /**
     * Opcion "Modificar cliente" del menu de clientes, contiene lo relativo a cambiar un unico cliente.
     */
    public void menuModificarClientes(){
        
        Scanner scan = new Scanner(System.in);
        boolean excepcion = true;
        String opcion;
        int opcionInt = 0;
        
        do{
            
            listarClientes();
            
            System.out.println("==============================================================");
            System.out.println("\t\t\tMENU MODIFICAR CLIENTE");
            System.out.println("==============================================================");
            System.out.println();
            System.out.println("1) Modificar cliente.");
            System.out.println("2) Registrar cliente.");
            System.out.println("3) Borrar cliente.");
            System.out.println();
            System.out.println("4) Volver al menu de clientes.");
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
                    modificarCliente();
                    break;
                    
                case 2:
                    registrarCliente();
                    break;
                    
                case 3:
                    borrarCliente();
                    break;
                    
                case 4:
                    System.out.println();
                    System.out.println("Saliendo al menu principal..");
                    System.out.println();
                    break;
                    
                default:
                    System.out.println();
                    System.out.println("Has introducido una opcion incorrecta, vuelve a intentarlo.");
                    break;
                
            }
            

        }while(opcionInt != 4);
        
    }
    
    /**
     * Opcion "Modificar cliente" del menu de modificar clientes, modifica un campo del cliente.
     */
    public void modificarCliente(){
        
        String contra;
        String atributo;
        Scanner scan = new Scanner(System.in);
        
        System.out.print("Introduzca la password del cliente a modificar: ");
        contra= scan.nextLine();
        
        for(int i=0; i<listaClientes.size(); i++){
            
            if(contra.equals(listaClientes.get(i).getPass())){
                
                for(int j=0; j<clienteAtributos.length; j++){
                    
                    System.out.println(clienteAtributos[j]);
                    
                }
                System.out.print("Introduzca el campo a modificar: ");
                atributo = scan.nextLine();
                
                switch(atributo){
                    
                    case "Nombre":
                        System.out.print("Introduzca el nuevo nombre: ");
                        listaClientes.get(i).setNombre(scan.nextLine());
                        break;
                        
                    case "Apellido1":
                        System.out.print("Introduzca el nuevo primer apellido: ");
                        listaClientes.get(i).setApellido1(scan.nextLine());
                        break;
                        
                    case "Apellido2":
                        System.out.print("Introduzca el nuevo segundo apellido: ");
                        listaClientes.get(i).setApellido2(scan.nextLine());
                        break;
                        
                    case "E-mail":
                        System.out.print("Introduzca el nuevo E-mail: ");
                        listaClientes.get(i).setMail(scan.nextLine());
                        break;
                        
                    case "Password":
                        System.out.print("Introduzca la nueva password: ");
                        listaClientes.get(i).setPass(scan.nextLine());
                        break;
                        
                    case "Direccion":
                        System.out.print("Introduzca la nueva direccion: ");
                        listaClientes.get(i).setDireccion(scan.nextLine());
                        break;
                        
                    case "Poblacion":
                        System.out.print("Introduzca la nueva poblacion: ");
                        listaClientes.get(i).setPoblacion(scan.nextLine());
                        break;
                        
                    case "Provincia":
                        System.out.print("Introduzca la nueva provincia: ");
                        listaClientes.get(i).setProvincia(scan.nextLine());
                        break;
                        
                    case "CP":
                        System.out.print("Introduzca el nuevo CP: ");
                        listaClientes.get(i).setCp(scan.nextLine());
                        break;
                        
                    case "Telefono":
                        System.out.print("Introduzca el nuevo telefono: ");
                        listaClientes.get(i).setTelefono(scan.nextLine());
                        break;
                        
                    default:
                        System.out.println("No has introducido el campo correctamente.");
                        System.out.println();
                        break;
                                        
                }
                break;
            }
        }
    }
    
    /**
     * Opcion "Registrar cliente" del menu de modificar clientes, añade un nuevo cliente.
     */
    public void registrarCliente(){
        
        Cliente c = new Cliente();
        Scanner scan = new Scanner(System.in);
        
        System.out.print("Introduzca el nombre: ");
        c.setNombre(scan.nextLine());
        
        System.out.print("Introduzca el primer apellido: ");
        c.setApellido1(scan.nextLine());
        
        System.out.print("Introduzca el segundo apellido: ");
        c.setApellido2(scan.nextLine());
        
        System.out.print("Introduzca el e-mail: ");
        c.setMail(scan.nextLine());
        
        System.out.print("Introduzca la provincia: ");
        c.setProvincia(scan.nextLine());
        
        System.out.print("Introduzca la poblacion: ");
        c.setPoblacion(scan.nextLine());
        
        System.out.print("Introduzca la direccion: ");
        c.setDireccion(scan.nextLine());
        
        System.out.print("Introduzca el codigo postal: ");
        c.setCp(scan.nextLine());
        
        System.out.print("Introduzca el telefono: ");
        c.setTelefono(scan.nextLine());
        
        System.out.print("Introduzca la contraseña: ");
        c.setPass(scan.nextLine());
        
        /*Una vez introducido el cliente lo metemos al arrayList*/
        listaClientes.add(c);
        
    }
    
    /**
     * Opcion "Borrar cliente" del menu de modificar clientes, borra el cliente a elegido.
     */
    public void borrarCliente(){
        
        Scanner scan = new Scanner(System.in);
        String contra;
        
        System.out.print("Introduzca el e-mail del cliente a modificar: ");
        contra = scan.nextLine();
        
        for(int i=0; i<listaClientes.size(); i++){
        
            if(contra.equals(listaClientes.get(i).getMail())){
                
                listaClientes.remove(i);
                break;
            
            }
        }
    }
    
    /**
     * Muestra los clientes con sus correos electronicos.
     */
    public void listarMailClientes(){
        
        /*Si la lista esta vacia ponemos error*/
        if(listaClientes.isEmpty()){
            
            System.out.println("No existe ningun cliente en la base de datos.");
            
        }
        else{

            System.out.println("\n");
            /*Ponemos los campos de cada cliente*/
            for(int i = 0; i<listaClientes.size(); i++){

                System.out.printf("%25s%25s", listaClientes.get(i).getNombre() + " " + listaClientes.get(i).getApellido1()
                                                            + " " + listaClientes.get(i).getApellido2(), listaClientes.get(i).getMail());
                System.out.println();

            }
            System.out.println();
            
        }
        
    }
    
    /*METODOS SETTER AND GETTER*/

    /**
     * 
     * @return ArrayList de los clientes
     */
    public ArrayList<Cliente> getListaClientes() {
        return listaClientes;
    }

    /**
     * 
     * @param listaClientes 
     */
    public void setListaClientes(ArrayList<Cliente> listaClientes) {
        this.listaClientes = listaClientes;
    }
    
    
    
}
