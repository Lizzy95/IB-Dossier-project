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
 * @(#)Libros.java
 *
 *Este dossier es un programa dedicada para el Laboratorio de Aislamiento Molecular mediante el cual
 *se hara agregaran cada uno de los materiales contenidos en el laboratorio de aislamiento moleculas para 
 *un inventario adecuado mediante el cual pueda manipular cada uno de los datos contenidos en el laboratorio.
 * 
 *@author: Norma Elizabeth Morales Cruz A01195888
 *
 * @version 1.00 2012/agosto/26
 *	modificado 2012/septiembre/30
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

public class Libros extends JFrame implements ActionListener
{
	private static  BufferedReader stdIn=new
 	BufferedReader(new InputStreamReader(System.in));
 	private static PrintWriter stdOut =new
 	PrintWriter(System.out, true);
   
    //Create and set up the window.
	JLabel LaNombre,LaClasi, LaClasificacion,LaCantidad, LaUbicacion, LaToxicidad, LaInformacion, LaFecha,LaFechad, LaNombrebd, LaNombrem, LaCantd;
	JTextField TxNombre,TxClasi, TxClasificacion, TxCantidad, TxUbicacion, TxToxicidad, TxInformacion, TxFecha,TxFechad, TxNombrebd, TxNombrem, TxCantd;
	JTextArea contacto, contactos, contac;
	JButton agregarbtn,borrarbtn, modificaragrebtn, modificardesbtn,modificarcant, modificarubi,desplegarbt;  		
	JPanel registro,buscar, modificar,desplegar;
	JComboBox tipos;//variables para crear una lista
	JButton buscartelbtn, buscarnombtn, ordenarbtn, buscabtn;
	JTabbedPane tabbedPane = new JTabbedPane();
	String numero="",memoria;//variables que evitan errores al designarles informacion
	boolean existe=false;
	int cant=0,I,v; 
	//estas variables son string debido a que contienen informacion de tipo texto para diversas caracteristicas del material    			 			   	
	String nombre="", currentPattern, ejemplo="", fech="";
	String clase="", fecha="",  clasificacion="";
	String dir="", ubi="", info="", toxi="";
	int opcion=0;	
	long tamRegistro=284;//varible mediante la cual esta definida el tamaño del archivo en bytes  
	long cantRegistros=0;//variable utilizada para comprobar longitud del archivo y leer los datos
	RandomAccessFile miArchivo=new RandomAccessFile ("Bibliografia.dat","rw"); // rw significa que el archive se podrá leer  y escribir

 /*
  *Clase mediante la cual se declaran todos los objetos de la ventana inventario de libros
  *mediante esta ventana se van agregando cada una de las opciones
  *
  */
 public Libros() throws IOException {
    	         
    	 super("INFORMACION BIBLIOGRAFICA");
         setSize(750, 700);
         setDefaultCloseOperation(EXIT_ON_CLOSE);  
        
        registro=new JPanel(new GridLayout(14,1, 3, 3));        
		//crea las etiquetas  y los campos de texto
		LaNombre = new JLabel("Nombre de material");
		TxNombre = new JTextField(40);
		LaClasificacion=new JLabel("Clasificacion de materiales");
		LaCantidad = new JLabel("Cantidad de material");
		TxCantidad = new JTextField(20);
		LaUbicacion=new JLabel("Ubicacion de materiales");
		TxUbicacion=new JTextField(30);
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
		
		//arreglo para la lista de clasificacion de informacion bibliografica
		String [] items={ "Clasificacion","Revistas", 
						"Libros", 
						"Publiciaciones cientificas", 
						"Tesis"};
						
		 currentPattern = items[0];
		tipos=new JComboBox(items);	
		tipos.addActionListener(this);

		//añade los componentes al panel registro
		registro.add(LaNombre);
		registro.add(TxNombre);
		registro.add(LaClasificacion);
		registro.add(tipos);
		registro.add(LaCantidad);
		registro.add(TxCantidad);
		registro.add(LaUbicacion);
		registro.add(TxUbicacion);;
		registro.add(LaFecha);
		registro.add(TxFecha);
		registro.add(LaInformacion);
		registro.add(TxInformacion);
		
		agregarbtn=new JButton("Agregar");				
		agregarbtn.setBackground(java.awt.Color.orange);
		agregarbtn.addActionListener(this);
		registro.add(agregarbtn);
				
        // se crea la ventana de buscar donde se agregaran las opciones para buscar un libro
        buscar=new JPanel(new GridLayout(14,1, 3, 3));
       	LaNombrebd = new JLabel("Nombre de material");
		TxNombrebd = new JTextField(40);
		LaClasi=new JLabel("Clasificacion de materiales");
		TxClasi=new JTextField(30);
		buscar.add(LaNombrebd);
		buscar.add(TxNombrebd);
		buscar.add(LaClasi);
		buscar.add(TxClasi);
		buscarnombtn = new JButton (" Buscar por nombre ");
		buscarnombtn.setBackground(java.awt.Color.lightGray);
		buscar.add(buscarnombtn);
		buscarnombtn.setVisible(true);
		buscarnombtn.addActionListener(this);
		// asigna al boton un listener	
	
	   //area de texto donde se desplegaran la informacion
	    contactos = new JTextArea("Material");
		contactos.setEditable(false);
		contactos.setForeground(Color.RED);
		contactos.setBackground(Color.white);
		//añade los componentes al panel buscar
		buscar.add(contactos);
		
		//añade los componentes en el nuevo panel de modificar
        modificar=new JPanel(new GridLayout(14,3,4,2));
       	LaNombrem = new JLabel("Nombre de material a modificar");
		TxNombrem = new JTextField(40);
		LaFechad=new JLabel("Fecha de modificacion");
		TxFechad=new JTextField(30);
		LaCantd=new JLabel("Nueva cantidad");							
		TxCantd=new JTextField(20);
		TxFechad.setText(fech);
		TxFechad.setEditable(false);

		modificaragrebtn=new JButton("Agregar cantidad");				
		modificaragrebtn.setBackground(java.awt.Color.orange);	
		modificaragrebtn.addActionListener(this);
		modificardesbtn=new JButton("Descontar uso");				
		modificardesbtn.setBackground(java.awt.Color.orange);	
		modificardesbtn.addActionListener(this);					
		//añade los componentes al panel modificar
		modificar.add(LaNombrem);
		modificar.add(TxNombrem);
		modificar.add(LaFechad);
		modificar.add(TxFechad);	
		modificar.add(LaCantd);
		modificar.add(TxCantd);
		modificar.add(modificaragrebtn);
		modificar.add(modificardesbtn);										
		contac = new JTextArea("Escribe el nombre del material a modificar y la nueva cantidad,\n posteriormente da click en modificar");
		contac.setEditable(false);
		contac.setForeground(Color.RED);
		contac.setBackground(Color.white);
		modificar.add(contac);
	
		//se crea el panel donde se desplegara la informacion
        desplegar=new JPanel();
		desplegarbt = new JButton("Desplegar todos los materiales");
		desplegar.add(desplegarbt);
		contacto = new JTextArea("Material",60,50);
		contacto.setEditable(false);
		contacto.setForeground(Color.RED);
		contacto.setBackground(Color.white);		
		desplegar.add(contacto);
		desplegarbt.addActionListener(this);						
	
		//se agregan cada una de los paneles como pestañas de la ventana principal de informacion bibliografica
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
	});//cierra opcion que lee el combobox de clasificaciones
	
	//opcion mediante la cual se iran agregando los materiales ingresados
	if(a.getSource()==agregarbtn)		
	{
		//se declaran vacios los cuadros de texto para evitar errores
		contactos.setText("");
		contac.setText("");
		contacto.setText("");	
		try
   		{
			RandomAccessFile miArchivo=new RandomAccessFile ("Bibliografia.dat","rw"); // rw significa que el archive se podrá leer  y escribir
	     	//inicializando variables para leer los datos de teclado
	       //capturando datos en el archivo
	      try
	      {
	      	
	      	 nombre=TxNombre.getText();
	      	 //clase=TxClasificacion.getText();
	      	 cant=Integer.parseInt(TxCantidad.getText());
	      	 ubi=TxUbicacion.getText();
	      	 info=TxInformacion.getText();
	      	 clase=ejemplo;
	      	 fecha=TxFecha.getText();
	      	
	      }
	      catch (NumberFormatException lfe)
	      	{
				   JOptionPane.showMessageDialog(null, "Se esperaba un numero", "ERROR",0);
			}	 

	        	// revisa si el string  del nombre es de 20 caracteres
	        		if (nombre.length()<40)
	  				{ 
	        			//si la longuitud es menor que 25 la completa con espacios
	        			for (I=nombre.length(); I<40;I++) 
	        			nombre=nombre + " ";
	        		}
	       	 		else
	        		{
	        			 //trunca la información a 20 caracteres si es que el nombre es mayor
	        			 nombre=nombre.substring(0,40);
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
	        		
	        	// revisa si el string  de la fecha es de 10 caracteres
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
	        			 //trunca la información a 20 caracteres si es que el nombre es mayor
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
	     		    miArchivo.writeChars(fecha);
	     			miArchivo.writeChars(info);

	     		nombre="";
	     		ubi="";
	     		clase="";
	     		info="";
	     		fecha="";
	     		cant=0;	
	     		TxNombre.setText("");		
	     		TxCantidad.setText("");	
	     		TxUbicacion.setText("");
	     		TxInformacion.setText("");
	     		TxFecha.setText(fech);	
	        			   	   			
	     	miArchivo.close();  // cierra el archive cuando ya no desea grabar mas
	  		}
   	  		catch (Exception nfe)
   	  			{
					JOptionPane.showMessageDialog(null, "Archivo no encontrado", "ERROR",0);
				}			
			}//cierra opcion de agregar
	//opcion mediante la cual se buscara de acuerdo al nombre dado
	if(a.getSource()==buscarnombtn)	
	{
		//se declaran vacios los cuadros de texto para evitar errores
		contactos.setText("");
		contac.setText("");
      	contacto.setText("");	
		try
		{		
			// pide el nombre  ha buscar
			nombre="";
			clase="";
			ubi="";
			cant=0;
			int registro=0;
			RandomAccessFile miArchivo=new RandomAccessFile ("Bibliografia.dat","rw");
							
			nombre=TxNombrebd.getText();
			nombre=nombre.trim();
			int linea=Buscar(nombre);
			if(linea>=0)
			{					 					
			 miArchivo.seek(linea*tamRegistro+80);
				for (int j=0; j<10;j++)
			    {	
					ubi=ubi + miArchivo.readChar();
				}
				contactos.setText("El material  "+nombre+" se encuentra "+ubi);
			}
			  else
				contactos.setText("No tienes ese material");	
						     
			miArchivo.close();	
			}
			catch(Exception nfe)
		   {
			JOptionPane.showMessageDialog(null, "Error al leer el archivo", "ERROR",0);
		   }								
		} //cierra opcion buscar por nombre del material
			
			//opcion mediante la cual se agregara a la cantidad
			if(a.getSource()==modificaragrebtn)
			{
				//se declaran vacios los cuadros de texto para evitar errores
				contactos.setText("");
		      	contac.setText("");
		      	contacto.setText("");	
				 try
		  		  {	
		  			// pide el nombre  ha buscar
					int registro=0;
					int newcant=0;
					String newfech="";
		  			nombre="";
					clase="";
					cant=0;
					RandomAccessFile miArchivo=new RandomAccessFile ("Bibliografia.dat","rw");
					try
					{
					contacto.setText("");
					nombre=TxNombrem.getText();
					nombre=nombre.trim();
					newfech=TxFechad.getText();
					newcant =Integer.parseInt(TxCantd.getText());

					}
				   catch(NumberFormatException nfe)
			   		{
			   	 		 System.out.println("Se esperaba un numero en cantidad");
			  		}
					int linea=Buscar(nombre);    
			  		if(linea>=0)
			   		{
			   			miArchivo.seek(linea*tamRegistro+204);
			   			miArchivo.writeChars(newfech);
			   			miArchivo.seek(linea*tamRegistro+200);	
					  	cant=miArchivo.readInt();
					  	newcant+=cant;					  		
					  	miArchivo.seek(linea*tamRegistro+200);	
					  	miArchivo.writeInt(newcant);
					  		
					  contacto.append("La nueva cantidad de "+nombre+"es "+newcant);

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
		}//se cierra la opcion mediante la cual se modifica agregandole la cantidad a un material
	
		//opcion mediante la cual se descuenta a la cantidad	
		if(a.getSource()==modificardesbtn)
			{		//se declaran vacios los cuadros de texto para evitar errores
				contactos.setText("");
		      	contac.setText("");
		      	contacto.setText("");	
				 try
		  		  {	
		  			// pide el nombre  ha buscar
					int registro=0;
					int newcant=0;
					String newfech="";
		  			nombre="";
					clase="";
					cant=0;
					RandomAccessFile miArchivo=new RandomAccessFile ("Bibliografia.dat","rw");
					try
					{
					contacto.setText("");
					nombre=TxNombrem.getText();
					nombre=nombre.trim();
					newfech=TxFechad.getText();
					newcant =Integer.parseInt(TxCantd.getText());

					}
				   catch(NumberFormatException nfe)
			   		{
				 	  JOptionPane.showMessageDialog(null, "Se esperaba un numero", "ERROR",0);
			  		}
					int linea=Buscar(nombre);    
			  		if(linea>=0)
			   		{
			   			miArchivo.seek(linea*tamRegistro+204);
			   			miArchivo.writeChars(newfech);
			   			miArchivo.seek(linea*tamRegistro+200);	
					  	cant=miArchivo.readInt();
					  	newcant=cant-newcant;					  		
					  	miArchivo.seek(linea*tamRegistro+200);	
					  	miArchivo.writeInt(newcant);
					  		
					  contacto.append("La nueva cantidad de "+nombre+"es "+newcant);

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

		//opcion mediante la cual se desplegara el material
		if(a.getSource()==desplegarbt)
		{	
			contactos.setText("");
		   	contac.setText("");
		   	contacto.setText("");	

			try
			{		
				// pide el nombre  ha buscar
				nombre="";
				clase="";
				ubi="";
				toxi="";
				info="";
				clasificacion="";						
				RandomAccessFile miArchivo=new RandomAccessFile ("Bibliografia.dat","rw");
				 cantRegistros=miArchivo.length()/tamRegistro;						
				//JOptionPane.showMessageDialog(null, "desplegando ","Título",1);						
			  		nombre="";
					clase="";
					ubi="";
					toxi="";
					info="";
					cant=0;
					clasificacion="";
				  	contacto.setText("");	  
				  	contacto.append("MATERIAl        "+"\t"+"        UBICACION  "+"\t" +"Clasificacion    "+"\t"+ "  CANTIDAD"+"\t"+"\t"+"FECHA "+"\t"+"INFORMACION "+"\n");     			
     				cantRegistros=miArchivo.length()/tamRegistro;
			 	 for (int registro=0;registro<cantRegistros; registro ++)
			   	 // posiciona el apuntador al inicio de cada registro
			   	 {  
				      miArchivo.seek(registro*tamRegistro);
				      //se lee el nombre del material
				      for (int j=0; j<40;j++)
			     		{	
	    	     			nombre=nombre + miArchivo.readChar();
			     		}	
			     	 // se lee la ubicacion del material
			     	 miArchivo.seek(registro*tamRegistro+80);   
					for (int j=0; j<30;j++)
					{	ubi=ubi + miArchivo.readChar();}
						 	
					miArchivo.seek(registro*tamRegistro+140);
					//se lee la clasificacion del material	
				   	for (int j=0; j<30;j++)	
				    {	clase=clase + miArchivo.readChar();}
						    
				    miArchivo.seek(registro*tamRegistro+200);
				    cant=miArchivo.readInt();
				    	
				 	miArchivo.seek(registro*tamRegistro+204);
				 	for (int j=0; j<10;j++)// se lee la fecha de ingreso
				 	{	fecha=fecha + miArchivo.readChar();}
				 	miArchivo.seek(registro*tamRegistro+224);
				 	for (int j=0; j<30;j++)// se lee la informacion adicional
				 	{	info=info + miArchivo.readChar();}
				   	contacto.append(nombre+ubi+clase+cant+"\t"+fecha+"\t"+info+"\n");
				    	
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
					JOptionPane.showMessageDialog(null, "Archivo no encontrado", "ERROR",0);
			   	}			
		}//cierra la opcion mediante la cual se depsliega			
    }//cierra action perfom
    
    /*
     *Clase mediante la cual se crea la ventana del inventario de libros
     *
     */
    public static void main(String[] args) throws IOException {
      Libros sc = new Libros();
		sc.setVisible(true); 	         	
        }
   
   /*
    *Metodo por medio del cual se realiza la busqueda la cual es llamada en buscar y modificar
    *
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
	
		RandomAccessFile miArchivo=new RandomAccessFile ("Bibliografia.dat","rw");
		 cantRegistros=miArchivo.length()/tamRegistro;
		while ( (registro<cantRegistros)&& (existe==false)) 
		{ // se posiciona al inicio donde empieza el nombre en cada registro
			miArchivo.seek(registro*tamRegistro);
			nom="";
			for( int i=0; i<40; i++)
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
   public static int Buscard(String name) throws IOException 
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
	
		RandomAccessFile miArchivo=new RandomAccessFile ("Bibliografia.dat","rw");
		 cantRegistros=miArchivo.length()/tamRegistro;
		while ( (registro<cantRegistros)&& (existe==false)) 
		{ // se posiciona al inicio donde empieza el nombre en cada registro
			miArchivo.seek(registro*tamRegistro+140);
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
  }	//cierra metodo
 }//se finaliza el programa de libros