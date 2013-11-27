/**
 *Norma Elizabeth Morales Cruz
 *Prepa Tec Campus Eugenio Garza Sada
 *
 *Windows 7 Enterprise. 
 *Computadora MAC particionada con Windows
 *5.5 Windows Experience Index
 *Intel(R) Core(TM) i5-2415M CPU @2.30GHz 2.30 GHz
 *4.00 GB (2.16 GB usable)
 *System type: 32-bit Operating System
 *Computer name: Lizzi-PC
 *
 *
 *JCreator
 *
 * @(#)Reactivos.java
 *
 *Este dossier es un programa dedicada para el Laboratorio de Aislamiento Molecular mediante el cual
 *se hara agregaran cada uno de los materiales contenidos en el laboratorio de aislamiento moleculas para 
 *un inventario adecuado mediante el cual pueda manipular cada uno de los datos contenidos en el laboratorio.
 *
 *
 *@author: Norma Elizabeth Morales Cruz A01195888
 *
 * @version 1.00 2012/agosto/11
 *	modoficaco 2012/septiembre/30
 */
package LAM;
import javax.swing.JTabbedPane;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JComponent;
import javax.swing.JButton;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.KeyEvent;
import javax.swing.*; 
import javax.swing.JTabbedPane;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JComponent;
import javax.swing.JButton;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.*;
import java.awt.*;
import java.applet.*;
import java.awt.Color;
import java.awt.event.*;
import javax.swing.*; 
import javax.swing.event.*;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class reactivos extends JFrame implements ActionListener
{
	private static  BufferedReader stdIn=new
 	BufferedReader(new InputStreamReader(System.in));
 	private static PrintWriter stdOut =new
 	PrintWriter(System.out, true);
 	
	//Crea todos los objetos utilizados en el inventario de reactivos
	JLabel LaNombre,LaNombred,LaNombrems, LaClasif, LaClasificacion,LaCantidad,LaCanti, LaUbicacion, LaToxicidad, LaInformacion, LaFecha, LaFechar;
	JTextField TxNombre,TxNombred,TxClasif,TxNombrems, TxClasificacion, TxCantidad,TxCanti, TxUbicacion, TxToxicidad, TxInformacion, TxFecha, TxFechar;
	JTextArea contacto,contactod, contactof, contactosd;
	JButton agregarbtn, borrarbtn, modificaragrebtn, modificardesbtn, desplegarbt;  		
	JPanel registro,buscar, modificar, desplegar;
	JComboBox tipos;//variable para crear una lista
	JButton buscartelbtn, buscarnombtn, ordenarbtn, buscarbtn;	         
    JTabbedPane tabbedPane = new JTabbedPane();
   	String numero="",memoria;//variables que evitan errores al designarles informacion
	boolean existe=false;
	int cant=0,I,v;   
	//estas variables son string debido a que contienen informacion de tipo texto para diversas caracteristicas del material    			 	
	String nombre="";
	String clase="", fecha="",currentPattern,  clasificacion="",ejemplo="";
	String dir="", ubi="", info="", toxi="", fech="";;
	int opcion=0;	
	long tamRegistro=284; //varible mediante la cual esta definida el tamaño del archivo en bytes 
	long cantRegistros=0;//variable utilizada para comprobar longitud del archivo y leer los datos
	RandomAccessFile miArchivo=new RandomAccessFile ("Reactivo.dat","rw"); // rw significa que el archive se podrá leer  y escribir

 /*
  *Clase mediante la cual se declaran todos los objetos de la ventana inventario de reactivos
  *mediante esta ventana se van agregando cada una de las opciones
  *
  */
    public reactivos() throws IOException  {
    	
    	 super("REACTIVOS DE ANALISIS");
         setSize(750, 700);
         setDefaultCloseOperation(EXIT_ON_CLOSE);//mediante esta opcion el programa se terminara al cerrar la ventana         
       
 
        registro=new JPanel(new GridLayout(14,1, 3, 3));        
		//crea las etiquetas  y los campos de texto
		LaNombre = new JLabel("Nombre del reactivo");
		TxNombre = new JTextField(20);
		LaClasificacion=new JLabel("Clasificacion de reactivos");
		LaCantidad = new JLabel("Cantidad de reactivos");
		TxCantidad = new JTextField(20);
		LaUbicacion=new JLabel("Ubicacion de reactivos");
		TxUbicacion=new JTextField(30);
		LaToxicidad= new JLabel("Grado de Toxicidad");
		TxToxicidad=new JTextField(20);
		LaFecha= new JLabel("Fecha de adquisición");
		TxFecha=new JTextField(20);
		LaInformacion= new JLabel("Informacion adicional importante");
		TxInformacion=new JTextField(30);
	
		//se crea la fecha actualizada de acuerdo al dia en que accede de esta manera el usuario no la ingresara
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();
		dateFormat.format(date);
		 fech= (dateFormat.format(date));
		TxFecha.setText(fech);
		TxFecha.setEditable(false);
		
		//arreglo para la lista de clasificacion de reactivos
		String [] items={ "Reactivos para analisis cualitativo", "Reactivos para revelar CCD"};
						
		 currentPattern = items[0];
		tipos=new JComboBox(items);
		
		tipos.addActionListener(this);
		//añade los componentes al panel del registro
		registro.add(LaNombre);
		registro.add(TxNombre);
		registro.add(LaClasificacion);
		registro.add(tipos);
		registro.add(LaCantidad);
		registro.add(TxCantidad);
		registro.add(LaUbicacion);
		registro.add(TxUbicacion);
		registro.add(LaToxicidad);
		registro.add(TxToxicidad);
		registro.add(LaFecha);
		registro.add(TxFecha);
		registro.add(LaInformacion);
		registro.add(TxInformacion);
		
		agregarbtn=new JButton("Agregar");				
		agregarbtn.setBackground(java.awt.Color.orange);
		agregarbtn.addActionListener(this);			
		registro.add(agregarbtn);
		
		//añade el panel a la ventana de tabbes
		tabbedPane.add("Registro", registro);
		
        // se crea la ventana de buscar donde se agregaran las opciones para buscar un libro      
        buscar = new JPanel(new GridLayout(14,1, 3, 3));
       	LaNombred = new JLabel("Nombre del Reactivo");
		TxNombred = new JTextField(20);
		LaClasif=new JLabel("Clasificacion de Reactivos");
		TxClasif=new JTextField(30);
	
		buscar.add(LaNombred);
		buscar.add(TxNombred);
		buscar.add(LaClasif);
		buscar.add(TxClasif);
		buscarnombtn = new JButton (" Buscar por nombre ");
		buscarnombtn.setBackground(java.awt.Color.lightGray);
		buscar.add(buscarnombtn);
		buscarnombtn.setVisible(true);
		// asigna al boton un listener
		buscarnombtn.addActionListener(this);
	   //area de texto donde se desplegaran la informacion
	    contactof =  new JTextArea ("Reactivos", 20, 70);
		contactof.setEditable(false);
		contactof.setForeground(Color.RED);
		contactof.setBackground(Color.white);
		//añade los componentes al panel buscar
		buscar.add(contactof);
        tabbedPane.add("Buscar", buscar);
	
		//añade los componentes en el nuevo panel de modificar
        modificar=new JPanel(new GridLayout(14,3,4,2));        
       	LaNombrems = new JLabel("Nombre del reactivo a modificar");
		TxNombrems = new JTextField(20);
		LaCanti = new JLabel("Cantidad de reactivos");
		TxCanti = new JTextField(20);
		LaFechar=new JLabel("Fecha de modificacion");
		TxFechar=new JTextField(30);
		TxFechar.setText(fech);
		TxFechar.setEditable(false);
		modificaragrebtn=new JButton("Agregar cantidad");				
		modificaragrebtn.setBackground(java.awt.Color.orange);	
		modificaragrebtn.addActionListener(this);
		modificardesbtn=new JButton("Descontar uso");				
		modificardesbtn.setBackground(java.awt.Color.orange);	
		modificardesbtn.addActionListener(this);
		//añade los componentes al panel modificar
		modificar.add(LaNombrems);
		modificar.add(TxNombrems);
		modificar.add(LaCanti);
		modificar.add(TxCanti);
		modificar.add(LaFechar);
		modificar.add(TxFechar);	
		modificar.add(modificaragrebtn);
		modificar.add(modificardesbtn);										
		contactod = new JTextArea("Reactivos", 20, 70);
		contactod.setEditable(false);
		contactod.setForeground(Color.RED);
		contactod.setBackground(Color.white);
		modificar.add(contactod);
	
		//se crea el panel donde se desplegara la informacion
	    desplegar= new JPanel();
		desplegarbt = new JButton("Desplegar todos los materiales");
		desplegarbt.addActionListener(this);
        contactosd = new JTextArea(30,50);
		contactosd.setEditable(false);
		desplegar.add(desplegarbt);
		desplegar.add(contactosd);
		tabbedPane.add("Desplegar Informacion", desplegar);

		//se agregan cada una de los paneles como pestañas de la ventana principal de reactivos
        tabbedPane.add("Modificar", modificar);         
    	tabbedPane.addTab("Registro", null, registro);
   		tabbedPane.addTab("Busqueda", null, buscar);
    	tabbedPane.addTab("Modificar", null, modificar);
    	tabbedPane.addTab("Desplegar informacion", null, desplegar);
		
   		getContentPane().add(tabbedPane, BorderLayout.CENTER);
    }
/*
 *En esta parte del programa se llevan a cabo cada una de las acciones de acuerdo a los botones
 *se realizan diversas acciones, al igual qeu de acuerdo a la pestaña en que se encuentra, es loq eu realizara
 */
public void actionPerformed(ActionEvent a) {
	
	//opcion mediante la cual se lee la opcion ingresada por medio de la lista	
	tipos.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent af) {
			JComboBox cb = (JComboBox)af.getSource();
			String newSelection = (String)cb.getSelectedItem();
			currentPattern = newSelection;
			ejemplo=currentPattern;
			System.out.println(ejemplo);
		}
	}
	);//opcion mediante la cual se leen las opciones de clasificacion
	
		//opcion mediante la cual se van agregando los materiales ingresados
     	if(a.getSource()==agregarbtn)
     	{
     	try
   			 {
			RandomAccessFile miArchivo=new RandomAccessFile ("Reactivo.dat","rw"); // rw significa que el archive se podrá leer  y escribir
	     	//inicializando variables para leer los datos de teclado
	       //capturando datos en el archivo
	  	    try
	       {
	      	contactod.setText("");
	      	contactof.setText("");
	      	contactosd.setText("");
	      	 nombre=TxNombre.getText();
	      	 //clase=TxClasificacion.getText();
	      	 cant=Integer.parseInt(TxCantidad.getText());
	      	 ubi=TxUbicacion.getText();
	      	 toxi=TxToxicidad.getText();
	      	 info=TxInformacion.getText();
	      	 clase=ejemplo;
	      	 fecha=TxFecha.getText();
	      	
	      }
	      catch (NumberFormatException lfe)
	      	{
				   JOptionPane.showMessageDialog(null, "Se esperaba un numero", "ERROR",0);
			}	 

	        	// revisa si el string  del nombre es de 20 caracteres
	        		if (nombre.length()<20)
	  				{ 
	        			//si la longuitud es menor que 20 la completa con espacios
	        			for (I=nombre.length(); I<20;I++) 
	        			nombre=nombre + " ";
	        		}
	       	 		else
	        		{
	        			 //trunca la información a 20 caracteres si es que el nombre es mayor
	        			 nombre=nombre.substring(0,20);
	        		}
	        		//revisa si la ubicacion es de 30 caracteres
	        		if (ubi.length()<30)
	  				{ 
	        			//si la longuitud es menor  la completa con espacios
	        			for (I=ubi.length(); I<30;I++) 
	        			ubi=ubi + " ";
	        		}
	       	 		else
	        		{
	        			 //trunca la información a 30 caracteres si es que el nombre es mayor
	        			ubi=ubi.substring(0,30);
	        		}
	        		
	        		//revisa si la direccion es de 30 caractares
	        		if (clase.length()<30)
	  				{ 
	        			//si la longuitud es menor  la completa con espacios
	        			for (I=clase.length(); I<30;I++) 
	        			clase=clase + " ";
	        		}
	       	 		else
	        		{
	        		 //trunca la información a 30 caracteres si es que la direccion es mayor
	        			 clase=clase.substring(0,30);
	        		}
	        		
	        	// revisa si el string  de la toxicidad es de 20 caracteres
	        		if (toxi.length()<20)
	  				{ 
	        			//si la longuitud es menor que 20 la completa con espacios
	        			for (I=toxi.length(); I<20;I++) 
	        			toxi=toxi + " ";
	        		}
	       	 		else
	        		{
	        			 //trunca la información a 20 caracteres si es que el nombre es mayor
	        			 toxi=toxi.substring(0,20);
	        		}
	        		if (fecha.length()<10)
	  				{ 
	        			//si la longuitud es menor  la completa con espacios
	        			for (I=fecha.length(); I<10;I++) 
	        			fecha=fecha + " ";
	        		}
	       	 		else
	        		{
	        			 //trunca la información a 10 caracteres si es que el nombre es mayor
	        			fecha=fecha.substring(0,10);
	        		}
	        	// revisa si el string  que la informacion sea de 30 carecteres 
	        		if (info.length()<30)
	  				{ 
	        			//si la longuitud es menor que 30 la completa con espacios
	        			for (I=info.length(); I<30;I++) 
	        			info=info + " ";
	        		}
	       	 		else
	        		{
	        			 //trunca la información a 30 caracteres si es que el nombre es mayor
	        			 info=info.substring(0,30);
	        		}
	        		
	        		
	        		
	     			//grabando en el archivo
	     			if (miArchivo.length()!=0)
	     			miArchivo.seek(miArchivo.length()); // posiciona al final del archivo el cursor o apuntador
	     			//empieza a grabrar los datos en el archivo
	     			miArchivo.writeChars(nombre);
	     			miArchivo.writeChars(ubi);
	     			miArchivo.writeChars(clase);
	     			miArchivo.writeInt(cant);
	     			miArchivo.writeChars(toxi);
	     		    miArchivo.writeChars(fecha);
	     			miArchivo.writeChars(info);
	     						
	     			
	     		nombre="";
	     		ubi="";
	     		clase="";
	     		toxi="";
	     		info="";
	     		fecha="";	
	     		TxNombre.setText("");		
	     		TxCantidad.setText("");	
	     		TxUbicacion.setText("");
	     		TxToxicidad.setText("");
	     		TxInformacion.setText("");
	     		TxFecha.setText(fech);	
	        		
	   	   		
	   	   			
	     	miArchivo.close();  // cierra el archive cuando ya no desea grabar mas
	  		}
   	  		catch (Exception nfe)
   	  		{
				JOptionPane.showMessageDialog(null, "Archivo no encontrado", "ERROR",0);
			}			     		
     	}//CIERRA OPCION DE AGREGAR
     	
     	//opcion mediante la cual se busca de acuerdo al nombre del material
     	if(a.getSource()==buscarnombtn)	
		{
			try
			{	
			contactod.setText("");
	      	contactof.setText("");
	      	contactosd.setText("");	
			 		// pide el nombre  ha buscar
			 		nombre="";
			 		clase="";
			 		ubi="";
			 		 cant=0;
			 		int registro=0;
					RandomAccessFile miArchivo=new RandomAccessFile ("Reactivo.dat","rw");
				
					nombre=TxNombred.getText();
					nombre=nombre.trim();
					int linea=Buscar(nombre);
				  if(linea>=0)
			  	{					 					
				      miArchivo.seek(linea*tamRegistro+40);
				      for (int j=0; j<10;j++)
			     		{	
			     			ubi=ubi + miArchivo.readChar();
			     		}
			     	contactof.setText("El material  "+nombre+" se encuentra "+ubi);
					
					 		}
						  else
						  	contactof.setText("No tienes ese material");					     
					miArchivo.close();	
						}
						catch(Exception nfe)
					    {
					    	System.out.println("Error al leer el archivo");
					    }								
			} //cierra opcion buscas por 
			
			//se modificar la cantidad agregando cantidad a la anterior
			if(a.getSource()==modificaragrebtn)
			{
			    	contactod.setText("");
				   	contactof.setText("");
			      	contactosd.setText("");
				 try
		  		  {	
		  			// pide el nombre  ha buscar
					int registro=0;
					int newcant=0;
					String newfech="";
		  			nombre="";
					clase="";
					cant=0;
					RandomAccessFile miArchivo=new RandomAccessFile ("Reactivo.dat","rw");
					try
					{
					contactod.setText("");
					nombre=TxNombrems.getText();
					nombre=nombre.trim();
					newfech=TxFechar.getText();
					newcant =Integer.parseInt(TxCanti.getText());

					}
				   catch(NumberFormatException nfe)
			   		{
			   	 		 System.out.println("Se esperaba un numero en telefono");
			  		}
					int linea=Buscar(nombre);    
			  		if(linea>=0)
			   		{ 
			   			//se lee la informacion
			   			miArchivo.seek(linea*tamRegistro+204);
			   			miArchivo.writeChars(newfech);	
			   			miArchivo.seek(linea*tamRegistro+160);	
					  	cant=miArchivo.readInt();
					  	newcant+=cant;					  		
					  	miArchivo.seek(linea*tamRegistro+160);	
					  	miArchivo.writeInt(newcant);
					  		
					  contactod.append("La nueva cantidad de "+nombre+"es "+newcant);

					}
					else
						System.out.println("No tienes ese material");	
				  nombre="";
				  clase="";
	    	 	  cant=0;
	
			 	miArchivo.close();
		  	}//cierra el try
		  	catch( Exception nfee) 
		   	 {
					JOptionPane.showMessageDialog(null, "Archivo no encontrado", "ERROR",0);
	   	 	}
		}//se cierra la opcion mediante la cual se modifica un material
	
		//opcion mediante la cual se descuenta a la cantidad	
		if(a.getSource()==modificardesbtn)
			{
				contactod.setText("");
		      	contactof.setText("");
		      	contactosd.setText("");
					try
			  		  {	
			  			// pide el nombre  ha buscar
			  			int newcant=0;
						int registro=0;
						String newfech="";
			  			nombre="";
						clase="";
						cant=0;
						RandomAccessFile miArchivo=new RandomAccessFile ("Reactivo.dat","rw");
						try
						{
						contactod.setText("");
						nombre=TxNombrems.getText();
						nombre=nombre.trim();
						newfech=TxFecha.getText();
						newcant =Integer.parseInt(TxCanti.getText());
					
						}
					   catch(NumberFormatException nfe)
				   		{
				   	 		 System.out.println("Se esperaba un numero en telefono");
				  		}
						int linea=Buscar(nombre);    
				  		if(linea>=0)
				   		{
				   			miArchivo.seek(linea*tamRegistro+204);
			   				miArchivo.writeChars(newfech);	
			   				miArchivo.seek(linea*tamRegistro+160);	
					  		cant=miArchivo.readInt();
					  		newcant=cant-newcant;					  		
					  		miArchivo.seek(linea*tamRegistro+160);	
					  		miArchivo.writeInt(newcant);
					  		
					  contactod.append("La nueva cantidad de "+nombre+"es "+newcant);
						}
						else
							System.out.println("No tienes a ese amigo");	
					  nombre="";
					  clase="";
					  cant=0;
				     		      
				 	miArchivo.close();
			  	}//cierra el try
			  	catch( Exception nfee) 
			   	 {
					JOptionPane.showMessageDialog(null, "Archivo no encontrado", "ERROR",0);
			   	 }
		}//se cierra la opcion mediante la cual se modifica un material

		//opcion mediante la cual se van desplegando cada uno de los materiales
		if(a.getSource()==desplegarbt)
		{
			contactod.setText("");
	      	contactof.setText("");
	      	contactosd.setText("");
			try
			{		
				// se declaran vacias las variables para evitar problemas posteriormente
				nombre="";
				clase="";
				ubi="";
				toxi="";
				info="";
				clasificacion="";
				
				RandomAccessFile miArchivo=new RandomAccessFile ("Reactivo.dat","rw");						
				clasificacion="Solventes";
				clasificacion=clasificacion.trim();
				int linea=Clases(clasificacion);
				cantRegistros=miArchivo.length()/tamRegistro;
			  	
			  	//se vacian las variables para evitar las variables
			  		nombre="";
					clase="";
					ubi="";
					toxi="";
					info="";
					cant=0;
					clasificacion="";
				  	contactosd.append("");	
				  	contactosd.append("MATERIAL "+" UBICACION"+"\t"+"Clasificacion    "+"\t"+"\t"+" CANTIDAD  "+"\t"+"TOXICIDAD " +"\t"+"FECHA "+"\t"+"INFORMACION "+info+"\n");
     			
     				 cantRegistros=miArchivo.length()/tamRegistro;
				  	 for (int registro=0;registro<cantRegistros; registro ++)
			   		 // posiciona el apuntador al inicio de cada registro
			   		 {  
				      miArchivo.seek(registro*tamRegistro);
				      for (int j=0; j<20;j++)
			     		{	
	    	     			nombre=nombre + miArchivo.readChar();
			     		}	
			     	 // se lee la ubicacion
			     	 miArchivo.seek(registro*tamRegistro+40);   
					for (int j=0; j<30;j++)
					{	ubi=ubi + miArchivo.readChar();}
						 	
					miArchivo.seek(registro*tamRegistro+100);	
				   	for (int j=0; j<30;j++)	// se lee la clase   
				    {	clase=clase + miArchivo.readChar();}
						    
				    miArchivo.seek(registro*tamRegistro+160);
				    cant=miArchivo.readInt();
				    	
				 	miArchivo.seek(registro*tamRegistro+164);
				 	for (int j=0; j<20;j++)//se lee el nivel de toxicidad
				 	{	toxi=toxi + miArchivo.readChar();}
				 	miArchivo.seek(registro*tamRegistro+204);
				 	for (int j=0; j<10;j++)// se lee la fecha de ingreso
				 	{	fecha=fecha + miArchivo.readChar();}
				 	miArchivo.seek(registro*tamRegistro+224);
				 	for (int j=0; j<30;j++)// se lee la informacion adicional
				 	{	info=info + miArchivo.readChar();}
				 	
					//leyendo la cantidad
					
				   	//contacto.append("El material "+nombre+" se encuentra en "+ubi+" existentes  "+ cant+"  toxicidad de: " +toxi+" actualizado "+fecha+" con "+info+"\n");
					//JOptionPane.showMessageDialog(null, " "+nombre+ubi+cant+toxi+fecha+info , "Título",1);
				   if(cant!=-1)
				   {	
				   	contactosd.append(nombre+ubi+clase+"\t"+cant+"\t"+toxi+"\t"+fecha+"\t"+info+"\t"+"\n");
				   }	
				   	nombre="";
				   	ubi="";
				   	cant=0;
				   	toxi="";
				   	fecha="";
				   	info="";
			    	}

				     
				nombre="";
				   	ubi="";
				   	cant=0;
				   	toxi="";
				   	fecha="";
				   	info="";
				miArchivo.close();	
			  	}
			 
				catch(Exception nfe)
			    {
					JOptionPane.showMessageDialog(null, "ERROR AL LEER EL ARCHIVO", "ERROR",0);
			   	}
		}//cierra la opcion mediante la cual se depsliega							
     }//cierra opcion de action perfomant 
     
/*
*Clase mediante la cual se crea la ventana del inventario de libros
*
*/     
 public static void main(String[] args) throws IOException  {
     	 reactivos fom = new reactivos();
		fom.setVisible(true);	

        }

/*
*Metodo por medio del cual se realiza la busqueda la cual es llamada en buscar y modificar
*
*/        
public static int Buscar(String name) throws IOException 
{	int cant=0;
	String cla="";
	String	nom="";
	int registro=0;
	int posicion=0;
	int cont=0;
	long tamRegistro=284;
	long cantRegistros=0;
	boolean existe=false;
	  // abriendo archivo, capturando y grabando datos
	try 
	{//* Abriendo archivo/
	
		RandomAccessFile miArchivo=new RandomAccessFile ("Reactivo.dat","rw");
		 cantRegistros=miArchivo.length()/tamRegistro;
		while ( (registro<cantRegistros)&& (existe==false)) 
		{ // se posiciona al inicio donde empieza el nombre en cada registro
			miArchivo.seek(registro*tamRegistro);
			nom="";
			for( int i=0; i<20; i++)
				nom=nom + miArchivo.readChar(); //lee el nombre	
				nom=nom.trim();
				if (nom.equalsIgnoreCase(name)) 
				{// si el nombte buscado es igual al nombre leído del archivo
		       	existe=true;
		       	posicion=registro;
		   		}
		   		nom="";
				registro++;	
	 } //while
	miArchivo.close();	 
	} //try
	catch (EOFException e) 
	{			
	  JOptionPane.showMessageDialog(null, "Archivo no encontrado", "ERROR",0);
	}
	 if (existe==false)
	 	{
	 		return -1;
	 	}
	 else 
	 	return posicion;						 	
  }	//cierra metodo	
   
 /*
  *Metodo mediante el cual se busca de acuerdo a la clasificacion 
  */  
public static int Clases(String name) throws IOException 
{	int cant=0;
	String cla="";
	String	nom="";
	int registro=0;
	int posicion=0;
	int cont=0;
	long tamRegistro=284;
	long cantRegistros=0;
	boolean existe=false;
	  // abriendo archivo, capturando y grabando datos
	try 
	{//* Abriendo archivo*/
	
		RandomAccessFile miArchivo=new RandomAccessFile ("Reactivo.dat","rw");
		 cantRegistros=miArchivo.length()/tamRegistro;
		while ( (registro<cantRegistros)&& (existe==false)) 
		{ // se posiciona al inicio donde empieza el nombre en cada registro
			miArchivo.seek(registro*tamRegistro+100);
			nom="";
			for( int i=0; i<30; i++)
				cla=cla + miArchivo.readChar(); //lee el nombre	
				cla=cla.trim();
				if (cla.equalsIgnoreCase(name)) 
				{// si el nombte buscado es igual al nombre leído del archivo
		       	existe=true;
		       	posicion=registro;
		   		}
		   		cla="";
				registro++;	
	 } //while
	miArchivo.close();

	 
	} //try

	catch (EOFException e) 
	{			
		JOptionPane.showMessageDialog(null, "Archivo no encontrado", "ERROR",0);
	}
	 if (existe==false)
	 	{
	 		return -1;
	 	}
	 else 
	 	return posicion;						 	
  }	//cierra metodo de busqueda 
 }//se finaliza el programa de reactivos