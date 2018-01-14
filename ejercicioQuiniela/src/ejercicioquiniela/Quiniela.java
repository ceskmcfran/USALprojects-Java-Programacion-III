/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ejercicioquiniela;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
/**
 *
 * @author Equipo Echo (Grupo PB1)
 * Juan Bautista Arellano Bruno
 * Francisco Blázquez Matías
 * David Flores Barbero
 * Lucia Goyenechea S. de Castro
 * Victor Castilla Garrido
 * 
 */
public class Quiniela{
    //Atributos del Objeto Quiniela
    private String archivoDePartidos; // CONTIENE EL ARCHIVO DE LOS PARTIDA DE LA JORNADA A ACCEDER
    private String archivoDeResultados;// CONTIENE EL NOMBRE DE LOS RESULTADOS DE LA JORNADA A ACCEDER 
    List<String> partidos = new ArrayList<>();//ArrayList que contendrá los partidos de una jornada
    List<String> resultados = new ArrayList<>();//ArrayList que contendra los resultados de una jornada
    List<String> apuestas = new ArrayList<>();//ArrayList que contenda las apuestas que haremos de esa jornada
    private String jornada;//Contendra la jornada con la que tratamos
    private double dinero;//Contendra el dinero que podemos gastarnos para echar una quiniela
    private static final double Carton = 0.5;//Constante que contiene el precio base de un carton
    private int aciertos;//Numero de Aciertos que tenemos en nuestra Quiniela
    //------------------------------------------------------------------------//
    
    
    /*
    PedirArchivoDePartidos()
    ESTA METODO LO QUE HACE ES PEDIR AL USUARIO QUE LE INDIQUE POR TECLADO LA JORNADA DE LA LIGA EN LA QUE 
    DESEA APOSTAR. UNA VEZ VALIDADA A JORNADA, ESTA SE CONVIERTE A STRING, PARA PODER CONCATENARLA A LA PARTE COMUN
    DEL NOMBRE QUE TIENEN TODOS LOS ARCHIVOS QUE CONTIENEN LOS PARTIDOS, QUE ES: "PartidosJornada", QUEDANDO ASI POR 
    EJEMPLO SI HEMOS INTRODUCIDO QUE QUEREMOS LA JORNADA 1, "PartidosJornada1", POSTERIORMENTE LE AÑADIMOS EL ".txt"
    Y LO GUARDAMOS EN EL ATRIBUTO DEL OBJETO "ArchivoDePartidos"
    LA JORNADA TAMBIEN LA GUARDAMOS EN UN ATRIBRUTO DEL OBJETO PARA RECURRIR A ELLA DIRECTAMENTE DESPUES
    */
    public void PedirArchivoDePartidos(){
        //Ponemos los ArrayList vacios
        partidos.clear();
        apuestas.clear();
        resultados.clear();
        /*Varible auxiliar, que necesitamos para formar el nombre completo del archivo, es la parte comun del 
        nombre de los archivos que contienen los partidos
        */
        String nombreComunArchivosDePartidos = "PartidosJornada";
        //Variable Auxiliar, que contendra la jornada en forma int
        int temp;
        //Declaramos un Scanner 
        Scanner Scan = new Scanner(System.in);
        //Pedimos la jornada en la que vamos a apostar y validamos que sea una jornada valida (Entre 1 y 13)
        do{
        System.out.print("Seleccione la Jornada del 2015-2016 en la que desea apostar (1-13): ");
        temp = Scan.nextInt();
        }while((temp<=0)||(temp>13));
        //Convertimos la jornada de tipo Int a tipo String, y la guardamos en el Atributo Jornada para usos posteriores
        setJornada(Integer.toString(temp));
        /*Formamos el nombre completo del archivo de partidos de la jornada a la que vamos a acceder y lo guardamos en
        la variable ArchivoDePartidos
        */
        setArchivoDePartidos(nombreComunArchivosDePartidos + getJornada() + ".txt");
        
    }
    
    /*
    PedirArchivoDeResultados()
    ESTE METODO ES EXACTAMENTE IGUAL A LA ANTERIOR, CON LA UNICA DIFERENCIA QUE NO NECESITAMOS PEDIR LA JORNADA
    PUES YA LA SABEMOS, YA QUE ESTA GUARDADA EN UN ATRIBUTO
    */
    public void PedirArchivoDeResultados(){
        /*Varible auxiliar, que necesitamos para formar el nombre completo del archivo, es la parte comun del 
        nombre de los archivos que contienen los resultados de los partidos*/
        String nombreComunArchivoDeResultados = "ResultadosJornada";
        //Formamos el nombre Comleto del archivo de los resultados de dicha jornada
        setArchivoDeResultados(nombreComunArchivoDeResultados+ getJornada() + ".txt");
    
    }
    
    /*
    LeerPartidos()
    ESTE METODO LEE LOS PARTIDOS DEL ARCHIVO DE LA JORNADA SELECCIONADA, SEGUN LEE UN PARTIDO, ESTE PARTIDO
    SE AÑADE AL FINAL DE UN ARRAYLIST LLAMADO "Partidos"
    FINALMENTE CIERRA EL ARCHIVO QUE HA ABIERTO AL PRINCIPIO DEL METODO
    */
    public void LeerPartidos() throws FileNotFoundException{
        //Creamos un objeto de la clase File para "enlazar con la ruta del archivo"
        File rutaPartido = new File(getArchivoDePartidos());
        //Creamos un objeto de la clase Scanner
        Scanner Scan = new Scanner(rutaPartido);
        //Variable Auxialiar Que contiene un solo partido y que se añade al ArrayList Partidos
        String partido;
        
        while(Scan.hasNextLine()){
            //El bucle se repite mientras haya partidos que leer
            partido = Scan.nextLine();
            partidos.add(partido); 
                
        }
        //Cerramos el archivo
        Scan.close();
    }
    
    /*
    LeerResultados()
    ESTA METODO LEE LOS PARTIDOS DEL ARCHIVO DE LA JORNADA SELECCIONADA, SEGUN LEE UN PARTIDO, ESTE PARTIDO
    SE AÑADE AL FINAL DE UN ARRAYLIST LLAMADO "Resultados"
    FINALMENTE CIERRA EL AARCHIVO QUE HA ABIERTO AL PRINCIPIO DEL METODO
    */
    public void LeerResultados() throws FileNotFoundException{
        //Creamos un objeto de la clase File
        File rutaResultados = new File(getArchivoDeResultados());
        //Creamos un objeto de la clase Scanner
        Scanner Scan = new Scanner(rutaResultados);
        //Variable Auxialiar Que contiene un solo resultado y que se añade al ArrayList Resultados
        String resultado;
        
        while(Scan.hasNextLine()){
            //El bucle se repite mientras haya resultados que leer
            resultado = Scan.nextLine();
            resultados.add(resultado);
        }
        Scan.close();
    }
    
    
    /*
    MostrarQuiniela();
    ESTE METODO REALIZA TRES TAREAS:
    1->LA PRIMERA DE TODAS ES COMPARAR DOS ARRAYLIST, EL QUE CONTIENE LOS RESULTADOS DE LA JORNADA (Resultados) Y 
    EL QUE CONTIENE LAS APUESTAS (Apuestas) QUE HEMOS REALIZADO PREVIAMENTE.
    2->LA SEGUNDA TAREA DERIVA DE ESTA PRIMERA, UNA VEZ QUE COMPARAMOS LOS ARRAYLIST, COMPROBAMOS SI EL RESULTADO
    ESTÁ CONTENIDO EN LA APUESTA. SI ES ASI, HEMOS TENIDO UN ACIERTO, POR LO QUE INCREMENTAMOS LOS ACIERTOS, SI NO NO.
    3->LA TERCERA ES IMPRIMIR LOS PARTIDOS, CON SU RESPECTIVO RESULTADO Y LA APUESTA QUE HEMOS REALIZADO DE DICHO
    PARTIDO
    */
    
    public void MostrarQuiniela(){
        //Reiniciamos a cero los resultados y desde un principio, siempre se puede hacer pleno al 15
        
        boolean PlenoAlQuince = true;
        setAciertos(0);
        System.out.println("\t\tPARTIDO\t\t\tRESULTADO\tAPUESTA");
        System.out.println("---------------------------------------------------------------");
        //Comenzamos a comparar los resultados con las apuestas
        
        for(int i=0; i < partidos.size(); i++){
                System.out.println("Partido "+(i+1));
               //Si la apuesta contiene el resultado, es un Acierto
                if(apuestas.get(i).contains(resultados.get(i))){
                    /*Lo 14 primeros son distintos del numero 15, pues el partido 15 depende de los 14 anteriores, 
                    de ahi que se evaluen de forma separada*/
                    if(i<partidos.size()-1){
                    //Incrementamos los aciertos
                    setAciertos(getAciertos()+1);
                    }
                    /*Que el partido numero 15 sea o no acierto, depende de lo que pongamos y de si hemos acertado los 14 partidos
                    Anteriores, si no los hemos acertado todos, da igual lo que hayamos puesto, contara como fallo,
                    por eso esa doble condicion*/
                    if((i+1==partidos.size())&&(PlenoAlQuince)){
                            setAciertos(getAciertos()+1);
                        
                    }
                    
                }else{
                    /*Si algun elemento i, al compararlo no es un acierto, ya no podemos hacer el pleno al quince, 
                    por eso lo ponemos a false*/
                    PlenoAlQuince = false;
                }
                //Imprimos el partido, la apuesta, y el resultado
                System.out.println(partidos.get(i)+"\t   "+resultados.get(i)+"\t\t  "+apuestas.get(i));
                //System.out.printf("%20s %10s %10s\n", partidos.get(i), apuestas.get(i), resultados.get(i));
                System.out.println("--------------------------------------------------------------");
        }
        
    }
    
    /*
    Dinero()
    ESTE METODO LO QUE HACE ES CALCULAR EL DINERO CON EL QUE VAMOS A PODER APOSTAR EN NUESTRA QUINIELA.
    LO CALCULAMOS SEGUN EL NUMERO DE DOBLES Y DE TRIPLES QUE GENEREAMOS, EN PARTE, DE FORMA ALEATORIA,
    SOLO EL NUMERO DE TRIPLES LO GENERAMOS DE ESTE METODO, EL NUMERO DE DOBLES QUE VAMOS A PODER PONER DEPENDE DEL NUMERO
    DE TRIPLES PRIMERAMENTE CALCULADOS, PUES POR EJEMPLO, CON 7 TRIPLES, SOLO SE PUEDEN PONER COMO MUCHO 4 DOBLES.
    ASI ESTA RECOGIDO EN LAS REGLAS DE LA QUINIELA. POR LO QUE SABIENDO QUE COMO MUCHO VAMOS A PODER PONER 4, GENERAMOS UN 
    NUMERO ALEATORIO ENTERO HASTA 4.
    UNA VEZ SACADOS LOS DOBLES Y TRIPLES, CALCULAMOS EL DINERO QUE VAMOS A PODER GASTARNOS COMO MUCHO. LO SACAMOS SEGUN LA 
    FORMULA CON LA QUE DEPENDE EL PRECIO DEL CARTON EN FUNCION DE LOS DOBLES Y TRIPLES, Y SE LO COMUNICAMOS AL USUARIO
    PARA QUE SE HAGA UNA IDEA DE LAS APUESTAS QUE PUEDE PONER EN LA QUINIELA QUE VA A JUGAR
    */
    @SuppressWarnings("empty-statement")
    public void Dinero(){
        
        //Creamos un objeto aleatorio con semilla de reloj.
        Random rand = new Random(System.nanoTime());
        //Creamos dos variables las cuales seran el numero maximo de triples y dobles.
        int maxTriples;
        double maxDobles = 0;
        
        /*Inicializamos el numero maximo de triples aleatoriamente entre 0 y 9.Porque por las reglas de la Quiniela
        no se pueden poner mas de 9*/
        maxTriples = rand.nextInt(10);
        
        /*Para cada caso aleatorio en "maxTriples" damos un valor aleatorio a los dobles
        siguiendo la tabla de la quiniela*/
        switch(maxTriples){
            
            case 9:
                maxDobles = 0;
                break;
            
            case 8:
                maxDobles = rand.nextInt(3);
                break;
                
            case 7:
                maxDobles = rand.nextInt(4);
                break;
            
            case 6:
                maxDobles = rand.nextInt(6);
                break;
                
            case 5:
                maxDobles = rand.nextInt(8);
                break;
            
            case 4:
                maxDobles = rand.nextInt(9);
                break;
                
            case 3:
                maxDobles = rand.nextInt(11);
                break;
            
            case 2:
                maxDobles = rand.nextInt(12);
                break;
                
            case 1:
                maxDobles = rand.nextInt(14);
                break;
                
            case 0:
                maxDobles = rand.nextInt(15);
                break;
                
        }
        
        /*Utilizamos la formula del coste de una quiniela para hallar el dinero maximo
        que puede tener el jugador, además utilizamos el metodo Math.pow(a,b) para 
        elevar los tipo "double"*/
        setDinero(Math.pow(3, maxTriples) * getCarton() * (Math.pow(2, maxDobles)));

        System.out.println("\nCuentas con "+getDinero()+" rupias.");
        System.out.println("Puedes poner "+(int )maxDobles+" dobles y "+maxTriples+" triples");
        System.out.println("Puedes combinarlos como quieras, pudiendo poner menos dobles y mas triples, o al reves,\n"
                + "mientras el precio de tu carton no sea superior a las rupias que tienes.");
        System.out.println("Presiona Enter para continuar... ");
        try{
            System.in.read();
            }catch(Exception e){};
        
    }
    
    /*
    Apuesta()
    LO QUE HACE ESTE METODO ES IR IMPRIMIENDO POR PANTALLA PARTIDO A PARTIDO Y RECOGIENDO NUESTRA APUESTA PARA DICHO 
    PARTIDO
    UNA VEZ RECOGIDA NUESTRA APUESTA PARA DICHO PARTIDO, LO PRIMERO ES VALIDAR QUE SEA UNA RESPUESTA VALIDA
    QUE NO HAYA CARACTERES INVALIDOS, QUE ESTE DENTRO DE LOS LIMITES ESTABLECIDOS...
    UNA VEZ QUE SABEMOS QUE LA RESPUESTA ES VALIDA, TENEMOS QUE SABER SI ES UN DOBLE, UN TRIPLE O UNA APUESTA SENCILLA
    Y TRAS SABER SI ES DOBLE, TRIPLE O APUESTA SENCILLA, TENEMOS QUE MULTIPLICAR EL PRECIO DEL CARTON, POR 2, POR 3, O 
    POR 1.
    TAMBIEN TENEMOS QUE TENER EN CUENTA SI EL PRECIO DEL CARTON ES MAYOR QUE EL DINERO QUE TENEMOS, SI SUCEDE ESTE CASO, 
    REINICIAREMOS LAS APUESTAS, Y VOLVEREMOS A EMPEZAR A TECLEAR LAS APUESTAS DESDE EL PRINCIPIO
    */
    @SuppressWarnings("empty-statement")
    public void Apuesta(){
        System.out.println("COMENCEMOS CON LAS APUESTAS");
        System.out.println("-----------------------------------");
        /*Iniciamos a 0 las siguientes variables, pues,
        dobles contendra el numero de dobles que lleva el usuario
        triples contendra el numero de triples que lleva el usuario
        i es la variable que controla las iteraciones del bucle*/
        
        int i = 0, dobles = 0, triples = 0;
        //Contendra el precio de cada carton tras cada apuesta
        double precioCarton = 0;
        //Variable que contendra la apuesta que tecleamos para cada partido
        String apuesta = "";
        //Estos dos Strings contienen todas las posibles apuestas que puede teclear el usuario
        //Ya sean dobles , triples o apuestas simples
        String charApuesta = "1x2";
        String charApuesta1 = "12";
        //Creamos un objeto Scanner para lectura de atributos
        Scanner Scan = new Scanner(System.in);
        //Recorremos el vector partidos para que pueda hacer la apuesta de cada partido
        while(i < partidos.size()){
            /*Iniciamos el precio de la apuesta a 0.5 rupias (precio minimo) para ir
            incrementandolo segun se vayan apostando triples o dobles*/
            precioCarton = (Math.pow(3, triples) * getCarton() * (Math.pow(2, dobles)));
            
            //Si el precio de la apuesta no supera el dinero que tienes se ejecuta el if
            if(precioCarton <= getDinero()){
                
                System.out.println("----------------------------------------------------------------------------------");
                System.out.println("Dinero: "+getDinero()+" rupias"+"\t\t\t\tCoste del carton: "+precioCarton+" rupias");
                System.out.println("----------------------------------------------------------------------------------\n");
                System.out.println(partidos.get(i)+"\n");
                //Leemos la apuesta por teclado
                System.out.print("Cual va a ser su apuesta?(1xX2): ");
                apuesta = Scan.next();
                //Ponemos la apuesta a minusculas
                apuesta = apuesta.toLowerCase();
                //Comprobamos que haya caracteres o que no exceda de los tres posibles
                if((apuesta.length() == 0) || (apuesta.length() > 3)){
                    
                    System.out.println("Error: Longitud de la apuesta invalida.");

                }
                else{
                    //Comprobamos que la apuesta contiene los caracteres "1, x, 2"
                    if(charApuesta.contains(apuesta)||(charApuesta1.contains(apuesta))){
                        //Metemos la apuesta en el array "apuestas"
                        apuestas.add(apuesta);
                        //Si la apuesta contiene 2 caracteres detectamos que es un doble
                        if(apuesta.length() == 2){
                            //Aumentamos el atributo dobles para recalcular el precio del carton
                            dobles++;
                        }
                        //Si la apuesta contiene 3 caracteres detectamos que es un triple
                        else if(apuesta.length() == 3){
                            //Aumentamos el atributo triples para recalcular el precio del carton
                            triples++;
                        }
                        i++;
                    }
                    else{
                        
                        System.out.println("Error: Caracter(es) invalido");
                        
                    }
                }
            }
            //Si el precio del carton sobrepasa el dinero que tenemos
            else{
                
                System.out.println("NO TE QUEDA DINERO EN EL MONEDERO, REAJUSTA TU QUINIELA DESDE EL PRINCIPIO");
                //Borramos todas las apuestas que habiamos metido en la lista
                apuestas.clear();
                //Iniciamos el bucle desde el principio reiniciando el atributo i
                i = 0;
                //Reiniciamos el precio del carton
                precioCarton = getCarton();
                //Reiniciamos los triples y los dobles
                dobles = 0;
                triples = 0;
                try{
                System.in.read();
                    }catch(Exception e){};
            }
        }
        //Salimos del bucle exitosamente y se lo comunicamos al usuario
        System.out.println("SU QUINIELA HA SIDO REGISTRADA CON EXITO");
        try{
            System.in.read();
            }catch(Exception e){};
        
    }
    
    /*
    Premios()
    ESTE METODO NOS INDICA SI HEMOS GANADO DINERO CON NUESTRA QUINIELA
    SOLO GANAMOS DINERO SI HEMOS CONSEGUIDO 10 O MAS ACIERTOS, CUANTOS MAS ACIERTOS, MAS DINERO
    EL DINERO QUE VAMOS A GANAR LO GENRAMOS CON UNA FUNCION QUE DEPENDE DE LOS ACIERTOS. 
    LA FUNCION ES INVENTADA
    */
    public void Premios(){
        
        Random Rand = new Random(System.nanoTime());
        int numero_aleatorio;
        double premio;
        System.out.println("*****************************************");
        System.out.println("              PREMIOS");
        System.out.println("*****************************************");
        
        //Solo ganan dinero aquellos que acierten 10 o mas partidos
        
        if(getAciertos()<10){
            System.out.println("NO HAS GANADO NADA, GRACIAS POR JUGAR.");
            System.out.println("HAS TENIDO "+getAciertos()+" ACIERTOS");
          
        }
        else{
         //Generamos el premio a partir de una funcion "aleatoria", pero que dependa de los acietos que hemos tenido
            numero_aleatorio = (getAciertos()%10);
            premio = ((Rand.nextInt(20) + 1) * Math.pow((double) getAciertos(),(double) numero_aleatorio));
            System.out.println("HAS TENIDO "+getAciertos());
            System.out.println("FELICIDADES, USTES HA GANADO "+premio+" Rupias!!! GRACIAS POR JUGAR");
            
            
        }
        
    }
    /*
    VisualizarPartidos()
    IMPRIME SOLO LOS PARTIDOS DE LA JORNADA, PARA SABER LOS PARTIDOS QUE FORMAN PARTE DE LA QUINIELA
    */
    public void VisualizarPartidos(){
        
        System.out.println("\nEstos son los Partidos de la Jornada "+getJornada());
        for(int i = 0; i<partidos.size(); i++){
        System.out.println(""+partidos.get(i));
        }
    }
    
    
    //************************************************************************//
    //************************************************************************//
    
    
    //  GETTER AND SETTER DEL ATRIBUTO JORNADA
    //  SET -> LE DA EL VALOR AL ATRIBUTO
    //  GET -> NOS DEVULEVE EL VALOR DEL ATRIBUTO
    public void setJornada(String jornada){
        this.jornada = jornada;
    }
    
    public String getJornada(){
        return jornada;
    }
    //------------------------------------------------------------------------//
    
    // GETTER AND SETTER DEL ATRIBUTO ARCHIVODEPARTIDOS 
    //  SET -> LE DA EL NOMBRE COMPLETO AL ATRIBUTO
    //  GET -> NOS DEVULEVE EL VALOR DEL ATRIBUTO
    public void setArchivoDePartidos(String nombreCompletoDelArchivo){
        this.archivoDePartidos = nombreCompletoDelArchivo;
    }
    
    public String getArchivoDePartidos(){
        return archivoDePartidos;
    }
    //------------------------------------------------------------------------//
    
    
    /*GETTER AND SETTER DEL ATRIBUTO ARCHIVODEPARTIDOS 
      SET -> LE DA EL NOMBRE COMPLETO AL ATRIBUTO
      GET -> NOS DEVULEVE EL VALOR DEL ATRIBUTO
    */
    public void setArchivoDeResultados(String nombreCompletoDelArchivo){
        this.archivoDeResultados = nombreCompletoDelArchivo; 
    }
    
    public String getArchivoDeResultados(){
        return archivoDeResultados;
    }
    //------------------------------------------------------------------------//
    
    
    /*GETTER AND SETTER DEL ATRIBUTO Dinero 
      SET -> LE DA EL NOMBRE COMPLETO AL ATRIBUTO
      GET -> NOS DEVULEVE EL VALOR DEL ATRIBUTO
    */
    public void setDinero(double dinero){
        this.dinero = dinero;
    }
    
    public double getDinero(){
        return dinero;
    }
    //------------------------------------------------------------------------//
    
    
    /*GETTER DEL ATRIBUTO CARTON 
     GET -> NOS DEVULEVE EL VALOR DEL ATRIBUTO
    Nota-> Solo necesitamos el getter, no el setter, pues nunca vamos a modificar su 
    valor, por eso esta declarado como una constante con final
    */
    public double getCarton(){
        return Carton;
    }
    //------------------------------------------------------------------------//
    
    
    /*GETTER AND SETTER DEL ATRIBUTO ACIERTOS 
      SET -> LE DA EL NOMBRE COMPLETO AL ATRIBUTO
      GET -> NOS DEVULEVE EL VALOR DEL ATRIBUTO
    */
    public int getAciertos() {
        return aciertos;
    }

    public void setAciertos(int aciertos) {
        this.aciertos = aciertos;
    }
    
}
