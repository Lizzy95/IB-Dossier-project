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
 * @(#)Dosier.java
 *
 *Este dossier es un programa dedicada para el Laboratorio de Aislamiento Molecular mediante el cual
 *se hara agregaran cada uno de los materiales contenidos en el laboratorio de aislamiento moleculas para 
 *un inventario adecuado mediante el cual pueda manipular cada uno de los datos contenidos en el laboratorio.
 *
 *
 *@author: Norma Elizabeth Morales Cruz A01195888
 *
 * @version 1.00 2012/agosto/10
 *	modoficaco 2012/octubre/1
 */
package LAM;
import LAM.Libros.*;
import LAM.reactivos.*;
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
import java.util.*;

public class Dosier extends JFrame implements ActionListener, WindowListener
		
 {	
    private static  BufferedReader stdIn=new
 	BufferedReader(new InputStreamReader(System.in));
 	private static PrintWriter stdOut =new
 	PrintWriter(System.out, true);
 	
 	
	JLabel LaNombre, LaClasificacion,LaCantidad, LaUbicacion, LaToxicidad, LaInformacion, LaFecha;
	JTextField TxNombre, TxClasificacion, TxCantidad, TxUbicacion, TxToxicidad, TxInformacion, TxFecha;
	JTextArea contacto;
	JButton agregarbtn,buscarbtn, borrarbtn, ordenarbtn,modificarbtn, imprimirbtn, registrobtn;
	JButton buscarclabtn, buscarnombtn, clasebtn, borrabtn, buscabtn,modibtn,modificaragre, modificardes;
	JButton desplegarbt;
	JPanel busquedaPnl, contactoPnl, informacionPnl, funcionesPnl, panel, clasi, buscaPnl;
	JComboBox clasecb;//variable para crear una lista			
	String memoria,  currentPattern;//variables que evitan errores al designarles informacion
	boolean existe=false;
	int cant=0,I,v;
	//estas variables son string debido a que contienen informacion de tipo texto para diversas caracteristicas del material    	
	String nombre="",fech="", ejemplo="", desplegar="";
	String clase="", fecha="",  clasificacion="";
	String dir="", ubi="", info="", toxi="";
	int opcion=0;	
	long tamRegistro=284; //varible mediante la cual esta definida el tamaño del archivo en bytes
	long cantRegistros=0;//variable utilizada para comprobar longitud del archivo y leer los datos
	RandomAccessFile miArchivo=new RandomAccessFile ("Inventario.dat","rw"); // rw significa que el archive se podrá leer  y escribir

public static void main(String[] args) throws IOException
	{
		
		Dosier formd= new Dosier();// crea un objeto de la clase Dosier
		formd.setSize(500, 650);// establece las medidas de la ventana
		formd.setTitle ("L.A.M. INVENTARIO");// establece el título de la ventana
		formd.setVisible(true);//hace visible la ventana
	}
	/*
	 *En esta parte del programa se declaran las varibles comprendidas en el menu pricnipal
	 */
	public Dosier() throws IOException 
	{
	
	   panel=new JPanel(new GridLayout(14,1, 3, 3));//el Layout en que estaran acomodadas cada una de las opciones

		registrobtn=new JButton("Registrar");
		buscabtn=new JButton("Busqueda");
		borrabtn=new JButton("Eliminar");
		modibtn=new JButton("Modificar");
		clasebtn=new JButton("Desplegar informacion");
		panel.add(registrobtn);
		panel.add(buscabtn);
		panel.add(borrabtn);
		panel.add(modibtn);
		panel.add(clasebtn);
		registrobtn.setVisible(true);
		registrobtn.addActionListener(this);
		buscabtn.setVisible(true);
		buscabtn.addActionListener(this);
		borrabtn.setVisible(true);
		borrabtn.addActionListener(this);
		modibtn.setVisible(true);
		modibtn.addActionListener(this);
		clasebtn.setVisible(true);
		clasebtn.addActionListener(this);
		addWindowListener(this);
		add(panel);
		addWindowListener(this);
		add(panel);
	}
	
	/*
	 *En esta parte del programa se llevan a cabo cada una de las acciones de acuerdo a los botones
	 *se abren nuevas ventanas de dialogo con otras opciones
	 */
	public void actionPerformed(ActionEvent a)	//operaciones
	{
	/*
	 *En esta parte del programa se lleva a cabo el registrar un nuevo material
	 */				
	if(a.getSource()==registrobtn)
	{
		setLayout(new FlowLayout(FlowLayout.LEFT,30, 30));// establece el FlowLayout
		//crea las etiquetas  y los campos de texto
		LaNombre = new JLabel("Nombre de material");
		TxNombre = new JTextField(20);// se declara la cantidad de caracteres que contendra
		LaClasificacion=new JLabel("Clasificacion de materiales");		
		LaCantidad = new JLabel("Cantidad de material");
		TxCantidad = new JTextField(10);// se declara la cantidad de caracteres que contendra
		LaUbicacion=new JLabel("Ubicacion de materiales");
		TxUbicacion=new JTextField(30);// se declara la cantidad de caracteres que contendra
		LaToxicidad= new JLabel("Grado de Toxicidad");
		TxToxicidad=new JTextField(30);// se declara la cantidad de caracteres que contendra
		LaFecha= new JLabel("Fecha de adquisición");
		TxFecha=new JTextField(10);// se declara la cantidad de caracteres que contendra
		LaInformacion= new JLabel("Informacion adicional importante");
		TxInformacion=new JTextField(30);

		//se crea la fecha actualizada de acuerdo al dia en que accede de esta manera el usuario no la ingresara
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();
		dateFormat.format(date);
		 fech= (dateFormat.format(date));
		TxFecha.setText(fech);
		TxFecha.setEditable(false);
		
		//arreglo establecido para la lista de clasificaciones
		String [] items={ "Clasificacion","Soportes cromatograficas", 
						"Material de oficina y limpieza", 
						"Material de vidrio", 
						"Solventes","Aparatos", "Estractos Vegetales", "Material Fresco", "Micropipetas"};
						
		 currentPattern = items[0];
		clasecb=new JComboBox(items);//se crea la lista
		
		clasecb.addActionListener(this);
		// Crea un panel que tiene 14 renglones 1 columnas, con 3 pixeles de separación
		contactoPnl = new JPanel(new GridLayout(14,1, 3, 3));
		//añade los componentes al panel contactoPn1
		contactoPnl.add(LaNombre);
		contactoPnl.add(TxNombre);
		contactoPnl.add(LaClasificacion);
		contactoPnl.add(clasecb);
		contactoPnl.add(LaCantidad);
		contactoPnl.add(TxCantidad);
		contactoPnl.add(LaUbicacion);
		contactoPnl.add(TxUbicacion);
		contactoPnl.add(LaToxicidad);
		contactoPnl.add(TxToxicidad);
		contactoPnl.add(LaFecha);
		contactoPnl.add(TxFecha);
		contactoPnl.add(LaInformacion);
		contactoPnl.add(TxInformacion);		
		
		//añade el panel a la ventana
		add(contactoPnl);
		
		agregarbtn=new JButton("Agregar");//se crea el boton agregar				
		agregarbtn.setBackground(java.awt.Color.orange);			
		contactoPnl.add(agregarbtn);				
		add(contactoPnl);
		agregarbtn.addActionListener(this);
		addWindowListener(this);			
	   		
		//se declara la nueva ventana donde apareceran las opciones de registrar	
		JDialog form = new JDialog();
		form.setBounds(300, 100, 700,500);
		form.setVisible(true);
		form.add(contactoPnl);//se añade el panel contactoPnl a la JDialog
		form.setTitle("Registro");//se da u titulo a la nueva ventana
		
	//opcion mediante la cual se lee la opcion ingresada por medio de la lista	
	clasecb.addActionListener(new ActionListener() {
    	public void actionPerformed(ActionEvent af) 
    	{
        	JComboBox cb = (JComboBox)af.getSource();
	        String newSelection = (String)cb.getSelectedItem();
	        currentPattern = newSelection;
	        ejemplo=currentPattern;//variable que se utilizara posteriormente para la clasificacion
	        System.out.println(ejemplo);
	    }
    });//opcion mediante la cual se leen las opciones de clasificacion
		agregarbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
			try
   			 {
			RandomAccessFile miArchivo=new RandomAccessFile ("Inventario.dat","rw"); // rw significa que el archive se podrá leer  y escribir
	     	//inicializando variables para leer los datos de teclado
	       //capturando datos en el archivo
	    	 try
	    	 {
	      	
	      	 nombre=TxNombre.getText();
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
	     						
	     		//se vacian las variables para evitar errores	
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
		 }
		}); //AQUI Acaba la opcion de agregar
				
	}//se cierra opcion de registro
	
	/*
	 *En esta parte del programa se lleva a cabo buscar
	 */	
	if(a.getSource()==buscabtn)
	{
		
		
		setLayout(new FlowLayout(FlowLayout.LEFT,60, 60));// establece el FlowLayout
		//crea las etiquetas  y los campos de texto
		LaNombre = new JLabel("Nombre de material");
		TxNombre = new JTextField(20);
		LaClasificacion=new JLabel("Clasificacion de materiales");
		TxClasificacion=new JTextField(30);
	
		// Crea un panel que tiene 10 renglones 1 columnas, con 3 pixeles de separación
		buscaPnl = new JPanel(new GridLayout(10,1, 3, 3));
		//añade los componentes al panel buscaPn1
		buscaPnl.add(LaNombre);
		buscaPnl.add(TxNombre);
		buscaPnl.add(LaClasificacion);
		buscaPnl.add(TxClasificacion);
		
		//añade el panel a la ventana
		add(buscaPnl);
			
		buscarnombtn = new JButton (" Buscar por nombre ");
		buscarclabtn= new JButton ("Buscar por clasificacion");
		buscarnombtn.setBackground(java.awt.Color.lightGray);
		buscarclabtn.setBackground(java.awt.Color.lightGray);

		// añade el botón calcula al panel buscaPn1
		buscaPnl.add(buscarnombtn);
		buscaPnl.add(buscarclabtn);
		buscarclabtn.setVisible(true);
		buscarnombtn.setVisible(true);


		// añade el panel al gridLayout
		add(buscaPnl);
		// asigna al boton un listener
		buscarnombtn.addActionListener(this);
		buscarclabtn.addActionListener(this);
		
		// añade a la ventana un listener
		addWindowListener(this);
	
	   //area de texto donde se desplegaran la informacion
	    contacto = new JTextArea("Material", 60, 60);
		contacto.setEditable(false);
		contacto.setForeground(Color.RED);
		contacto.setBackground(Color.white);
		//añade los componentes al panel buscaPn1
		buscaPnl.add(contacto);
	
		//se declara la nueva ventana donde apareceran las opciones de buscar			
		JDialog fuorm = new JDialog();
		fuorm.setBounds(300, 100, 700,600);
		fuorm.setVisible(true);
		fuorm.add(buscaPnl);//se añade el panel buscaPnl a la JDialog
		fuorm.setTitle("Busqueda");
		
		//opcion mediante la cual se busca de acuerdo el nombre
		buscarnombtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent av) {
				try {
					// pide el nombre  ha buscar
					nombre="";
					clase="";
					ubi="";
					cant=0;
					int registro=0;
					RandomAccessFile miArchivo=new RandomAccessFile ("Inventario.dat","rw");
					nombre=TxNombre.getText();
					nombre=nombre.trim();
					int linea=Buscar(nombre);
					if(linea>=0) {
						miArchivo.seek(linea*tamRegistro+40);
						for (int j=0; j<10;j++) {
							ubi=ubi + miArchivo.readChar();
						}
						contacto.setText("El material  "+nombre+" se encuentra "+ubi);
					} else
						contacto.setText("No tienes ese material");
					miArchivo.close();
					buscarnombtn.setVisible(false);
					buscarclabtn.setVisible(false);
				}
				catch(Exception nfe) 
				{
					JOptionPane.showMessageDialog(null, "Archivo no encontrado", "ERROR",0);
				}
			}
		});//cierra opcion buscas por nombre del material
			
		buscarclabtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent av) {
				try {
					// pide el nombre  ha buscar
					nombre="";
					clase="";
					cant=0;
					int registro=0;
					RandomAccessFile miArchivo=new RandomAccessFile ("Inventario.dat","rw");
					nombre=TxClasificacion.getText();
					nombre=nombre.trim();
					int linea=Clases(nombre);
					System.out.println(linea);
					if(linea>=0)
					{
					  miArchivo.seek(linea*tamRegistro+40); {
							ubi=ubi + miArchivo.readChar();
						}
						cant=miArchivo.readInt();
						contacto.setText("Los materiales de esta clase son "+nombre+" ubicados en "+ubi);
					} 
					else
						contacto.setText("No tienes ese material");
					miArchivo.close();
					buscarnombtn.setVisible(false);
					buscarclabtn.setVisible(false);
				}
				//cierra el try
				catch(Exception nfe) {
					JOptionPane.showMessageDialog(null, "Error al leer el archivo", "ERROR",0);
				}
			}
		});//AQUI la opcion de buscar por clasificacion
	}//cierra la opcion de buscar
	
	
	if(a.getSource()==modibtn)
	{
		setLayout(new FlowLayout(FlowLayout.LEFT,20, 20));// establece el FlowLayout
		//crea las etiquetas  y los campos de texto
		LaNombre = new JLabel("Nombre de material a modificar");
		TxNombre = new JTextField(20);
		LaCantidad=new JLabel("Cantidad que se uso o se adquirio");
		TxCantidad=new JTextField(30);
		LaFecha=new JLabel("Fecha de modificacion");
		TxFecha=new JTextField(10);
		LaFecha.setVisible(true);
		TxFecha.setVisible(true);

		LaCantidad.setVisible(true);
		TxCantidad.setVisible(true);
		//se crea la fecha actualizada
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date = new Date();
		dateFormat.format(date);
		String fech= (dateFormat.format(date));
		TxFecha.setText(fech);
		TxFecha.setEditable(false);
		// Crea un panel que tiene 8 renglones 1 columnas, con 3 pixeles de separación
		contactoPnl = new JPanel(new GridLayout(8,1, 3, 3));
		//añade los componentes al panel datosPn1
		contactoPnl.add(LaNombre);
		contactoPnl.add(TxNombre);
		contactoPnl.add(LaFecha);
		contactoPnl.add(TxFecha);						
		contactoPnl.add(LaCantidad);
		contactoPnl.add(TxCantidad);
				
		//añade el panel a la ventana
		add(contactoPnl);
				
		modificaragre=new JButton("Adquisicion");				
		modificaragre.setBackground(java.awt.Color.orange);			
		modificardes=new JButton("Uso");				
		modificardes.setBackground(java.awt.Color.orange);			
		
		contactoPnl.add(modificardes);
		contactoPnl.add(modificaragre);				
		modificardes.addActionListener(this);
		addWindowListener(this);
		modificaragre.addActionListener(this);
		addWindowListener(this);
		
		contacto = new JTextArea("Dame el nombre de la persona a la cual le quieres modificar"+"\n", 40, 40);
		contacto.append("Dame la cantidad o la nueva ubicacion que deseas modificar."+"\n");
		nombre=TxNombre.getText();
		nombre=nombre.trim();
	

		contacto.setEditable(false);
		contacto.setForeground(Color.RED);
		contacto.setBackground(Color.white);

		contactoPnl.add(contacto);
		
		//se declara la nueva ventana donde apareceran las opciones de modificar		
		JDialog farm = new JDialog();
		farm.setBounds(300, 100, 700,500);
		farm.setVisible(true);
		farm.add(contactoPnl);//se añade el panel contactoPnl a la JDialog
		farm.setTitle("Modificar");//se establece titulo a la nueva ventana
		
	/*
	 *En este metodo se activa la opcion de agregar material a los que actualmente existen
	 */
		modificaragre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				 try
		  		  {	
		  			// pide el nombre  ha buscar
					int registro=0;
					int newcant=0;
					String newfech="";
		  			nombre="";
					clase="";
					cant=0;
					RandomAccessFile miArchivo=new RandomAccessFile ("Inventario.dat","rw");
					try
					{
					contacto.setText("");
					nombre=TxNombre.getText();
					nombre=nombre.trim();
					newfech=TxFecha.getText();
					newcant =Integer.parseInt(TxCantidad.getText());

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
					  		newcant+=cant;					  		
					  		miArchivo.seek(linea*tamRegistro+160);	
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
		  }
		});
		
	/*
	 *En este metodo se activa la opcion de descontar material a los que actualmente existen
	 */		modificardes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent afe)
			{
					JOptionPane.showMessageDialog(null, " Modificar cant", "Título",1);
					try
			  		  {	
			  			// pide el nombre  ha buscar
			  			int newcant=0;
						int registro=0;
						String newfech="";
			  			nombre="";
						clase="";
						cant=0;
						RandomAccessFile miArchivo=new RandomAccessFile ("Inventario.dat","rw");
						try
						{
						contacto.setText("");
						nombre=TxNombre.getText();
						nombre=nombre.trim();
						newfech=TxFecha.getText();
						newcant =Integer.parseInt(TxCantidad.getText());
					
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
					  		cant=miArchivo.readInt();
					  		newcant=cant-newcant;					  		
					  		miArchivo.seek(linea*tamRegistro+160);	
					  		miArchivo.writeInt(newcant);
					  		
					  contacto.append("La nueva cantidad de "+nombre+"es "+newcant);
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
			   }
			});//se cierra el descontar al material
		
	}//cierra la opcion de modificar
	
/*
 *Se inicia la opcion de borrar un material, con lainterfaz y el codigo
 */
	if(a.getSource()==borrabtn)
	{		
		
		setLayout(new FlowLayout(FlowLayout.LEFT,20, 20));// establece el FlowLayout
		//crea las etiquetas  y los campos de texto
		LaNombre = new JLabel("Nombre de material a borrar");
		TxNombre = new JTextField(20);
	
		// Crea un panel que tiene 5 renglones 2 columnas, con 3 pixeles de separación
		contactoPnl = new JPanel(new GridLayout(8,1, 3, 3));
		//añade los componentes al panel datosPn1
		contactoPnl.add(LaNombre);
		contactoPnl.add(TxNombre);
		
		//añade el panel a la ventana
		add(contactoPnl);
		
		borrarbtn=new JButton("Borrar material");				
		borrarbtn.setBackground(java.awt.Color.orange);			


		contactoPnl.add(borrarbtn);				
		add(contactoPnl);
		borrarbtn.addActionListener(this);
		addWindowListener(this);
	
	   //area de texto donde se desplegaran la informacion
	    contacto = new JTextArea("Material", 10, 70);
		contacto.setEditable(false);
		contacto.setForeground(Color.RED);
		contacto.setBackground(Color.white);

		//añade los componentes al panel contactoPn1
		contactoPnl.add(contacto);

		//se declara la nueva ventana donde apareceran las opciones de eliminar							
		JDialog ferm = new JDialog();
		ferm.setBounds(300, 100, 700,500);
		ferm.setVisible(true);
		ferm.add(contactoPnl);//se añade el panel contactoPnl a la JDialog
		ferm.setTitle("Eliminar material");//se establece titulo a la ventana
		
		//metodo mediante el cual se borra el material
		borrarbtn.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e)
		{
		  try
		 	{		
		 		// pide el nombre  ha buscar
		 		//se vacian las variables para evitar errores
		 		nombre="";
		 		clase="";
		 		ubi="";
		 		toxi="";
		 		info="";
		 		fecha="";
		 		cant=0;
		 		int registro=0;
				RandomAccessFile miArchivo=new RandomAccessFile ("Inventario.dat","rw");
				contacto.setText("");
				contacto.append("Dame el nombre de la persona que quieres borrar: ");
				nombre=TxNombre.getText();
				nombre=nombre.trim();
				int linea=Buscar(nombre);
				if(linea>=0)
			  	{
				  
				     miArchivo.seek(linea*tamRegistro);
				      	for (I=nombre.length(); I<20;I++)
				      	{
				      	miArchivo.writeChars(" "); 
				   		nombre="";
				      	}
				      	 miArchivo.seek(linea*tamRegistro+40);
				       	for (I=ubi.length(); I<30;I++)
				      	{
				      	miArchivo.writeChars(" "); 
				   		ubi="";
				      	}
				     miArchivo.seek(linea*tamRegistro+100);
				      	for (I=clase.length(); I<30;I++)
				      	{
				      	miArchivo.writeChars(" "); 
				   		clase="";
				      	}
				     	miArchivo.seek(linea*tamRegistro+160);
				    	miArchivo.writeInt(-1);	
				    		
				      	 miArchivo.seek(linea*tamRegistro+164);
				      	for (I=toxi.length(); I<20;I++)
				      	{
				      	miArchivo.writeChars(" "); 
				   		toxi="";
				      	}
				      	 miArchivo.seek(linea*tamRegistro+204);
				      	for (I=fecha.length(); I<10;I++)
				      	{
				      	miArchivo.writeChars(" "); 
				   		fecha="";
				      	}
				      	 miArchivo.seek(linea*tamRegistro+224);
				      	for (I=info.length(); I<30;I++)
				      	{
				      	miArchivo.writeChars(" "); 
				   		info="";
				      	}
				 JOptionPane.showMessageDialog(null, "Material Borrado ", "Título",1);     	
		 		}
			  else
			  contacto.setText("No tienes ese material");
			nombre="";
			miArchivo.close();
		 	}
		  	catch(Exception nfe)
		   	 {   
					JOptionPane.showMessageDialog(null, "Error al leer el archivo", "ERROR",0);
	   	 	 } 				
		   }
		 });
				
	}//cierra la opcion de eliminar
	
/*
 *En esta opcion se despliega la informacion y se ordena
 */	
	if(a.getSource()==clasebtn)//se declaran todos los campos necesarios de interfaz
	{
		
		clasi = new JPanel(); // se crea el panel de clasificacion
		desplegarbt = new JButton("Desplegar todos los materiales");
		clasi.add(desplegarbt);
		ordenarbtn=new JButton("Ordenar por clasificacion");				

		clasi.add(ordenarbtn);
			
		ordenarbtn.addActionListener(this);
		addWindowListener(this);

		contacto = new JTextArea("Material",60,50);
		contacto.setEditable(false);
		contacto.setForeground(Color.RED);
		contacto.setBackground(Color.white);		
		clasi.add(contacto);
	
		desplegarbt.addActionListener(this);
		addWindowListener(this);						
		
		add(clasi);			
		
		JDialog firm = new JDialog();
		firm.setBounds(300, 100, 860,600);
		firm.setVisible(true);
		firm.add(clasi);
		firm.setTitle("Clases");
		
		/*
		 *Opcion mediante la cual se despliega la informacion tal y como aparece en el archivo
		 */
		desplegarbt.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent uv)
		{
			try
			{		
				// pide el nombre  ha buscar
				nombre="";
				clase="";
				ubi="";
				toxi="";
				info="";
				clasificacion="";
				
						
				RandomAccessFile miArchivo=new RandomAccessFile ("Inventario.dat","rw");						
				clasificacion="Solventes";
				clasificacion=clasificacion.trim();
				int linea=Clases(clasificacion);
				cantRegistros=miArchivo.length()/tamRegistro;
						  	
			  		nombre="";
					clase="";
					ubi="";
					toxi="";
					info="";
					cant=0;
					clasificacion="";
				  	contacto.setText("");	
				  	contacto.append("MATERIAL "+" UBICACION"+"\t"+"Clasificacion    "+"\t"+"\t"+" CANTIDAD  "+"\t"+"TOXICIDAD " +"\t"+"FECHA "+"\t"+"INFORMACION "+info+"\n");
     			
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
						    
				    miArchivo.seek(registro*tamRegistro+160);// se lee la cantidad
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
				 	

				   if(cant!=-1)
				   {	
				   	contacto.append(nombre+ubi+clase+"\t"+cant+"\t"+toxi+"\t"+fecha+"\t"+info+"\t"+"\n");
				   }
				   //se vacian las variables para evitar errores	
				   	nombre="";
				   	ubi="";
				   	cant=0;
				   	toxi="";
				   	fecha="";
				   	info="";
				   	clase="";
			    	 }
					nombre="";
				   	ubi="";
				   	cant=0;
				   	toxi="";
				   	fecha="";
				   	info="";
				   	clase="";
				miArchivo.close();	
			  	}
			 	catch( FileNotFoundException e)
				{
					JOptionPane.showMessageDialog(null, "Archivo no encontrado", "ERROR",0);
				}
				catch(Exception nfe)
			    {
					JOptionPane.showMessageDialog(null, "Error al leer el archivo", "ERROR",0);
			   	}
		}
		});//cierra opcion de solventes
		
		ordenarbtn.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent uv)
		{
			contacto.setText("");
			tamRegistro=284;
			cantRegistros=0;

			Inverarg auxcont;//crea variable auxcont de clase Contacto

			try
			{
				RandomAccessFile miArchivo=new RandomAccessFile("Inventario.dat","rw");//abre archivo

				cantRegistros=miArchivo.length()/tamRegistro;//se calcula la cantidad de registros para saber el tamaño del arreglo

				Inverarg arrcont[]=new Inverarg[(int)cantRegistros];//se crea arreglo de contactos

				for(int registro=0; registro<cantRegistros; registro++)
				{	
						miArchivo.seek(registro*tamRegistro);

						arrcont[registro]=new Inverarg();//crea el constructor de la clase registro

						arrcont[registro].nombre="";//lee el nombre
						for(int j=0;j<20;j++)
							arrcont[registro].nombre=arrcont[registro].nombre+miArchivo.readChar();
						
						arrcont[registro].ubi="";//lee la ubicacion la guarda en el arreglo
						for(int j=0;j<30;j++)
							arrcont[registro].ubi=arrcont[registro].ubi+miArchivo.readChar();
						
						arrcont[registro].clase="";//lee la clase la guarda en el arreglo
						for(int j=0;j<30;j++)
							arrcont[registro].clase=arrcont[registro].clase+miArchivo.readChar();

						arrcont[registro].cant=miArchivo.readInt();//lee el telefono la guarda en el arreglo

						arrcont[registro].toxi="";//lee la toxicidad, la guarda en el arreglo
						for(int j=0;j<20;j++)
							arrcont[registro].toxi=arrcont[registro].toxi+miArchivo.readChar();

						arrcont[registro].fecha="";//lee la fecha y la guarda en el arreglo
						for(int j=0;j<10;j++)
							arrcont[registro].fecha=arrcont[registro].fecha+miArchivo.readChar();

						arrcont[registro].info="";//lee la informacin adicional y la guarda en el arreglo
						for(int j=0;j<30;j++)
							arrcont[registro].info=arrcont[registro].info+miArchivo.readChar();
	
				}
					miArchivo.close();//cierra archivo
					//se ordena el arreglo alfabeticamente
					String auxi, auxj;
					for (int i=0;i<cantRegistros; i++)
						for(int j=i;j<cantRegistros;j++)
						{
							auxi=arrcont[i].clase.toUpperCase();
							auxj=arrcont[j].clase.toUpperCase();
							if(auxi.compareTo(auxj)>0)
							{
								auxcont=arrcont[i];
								arrcont[i]=arrcont[j];
								arrcont[j]=auxcont;
							}
						}
					File arch=new File("Inventario.dat");
					arch.delete();//se borra el archivo original

					RandomAccessFile miArchivo2=new RandomAccessFile("Inventario.dat","rw");//se abre un nuevo archivo
					//se escriben los datos en el nuevo archivo
					for(int registro=0;registro<cantRegistros;registro++)
					{
						miArchivo2.writeChars(arrcont[registro].nombre);
						miArchivo2.writeChars(arrcont[registro].ubi);
						miArchivo2.writeChars(arrcont[registro].clase);
						miArchivo2.writeInt(arrcont[registro].cant);
						miArchivo2.writeChars(arrcont[registro].toxi);
						miArchivo2.writeChars(arrcont[registro].fecha);
						miArchivo2.writeChars(arrcont[registro].info);

					}
					miArchivo2.close();//cierra archivo

					contacto.append("Contactos ordenados.");
				}
				catch( FileNotFoundException e)
				{
					JOptionPane.showMessageDialog(null, "Archivo no encontrado", "ERROR",2);
				}
				catch (NumberFormatException e)
				{
				   JOptionPane.showMessageDialog(null, "Se esperaba un numero", "ERROR",2);
				}
				catch(IOException e)
				{
				  	JOptionPane.showMessageDialog(null, "ERROR", "ERROR",2);
				}
		} 				
	  });//cierra opcion de ordenar por clasificacion		
	}//cierra la opcion de clasificacion
}//cierra el metodo del inventario general
	

/*
 *Metodo por medio del cual se realiza la busqueda por clasificacion
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
	
		RandomAccessFile miArchivo=new RandomAccessFile ("Inventario.dat","rw");
		 cantRegistros=miArchivo.length()/tamRegistro;
		while ((registro<cantRegistros)&& (existe==false)) 
		{ // se posiciona al inicio donde empieza el nombre en cada registro
			
			miArchivo.seek(registro*tamRegistro+100);
			for( int i=0; i<30; i++)	
				cla=cla + miArchivo.readChar(); //lee el nombre	
				cla=cla.trim();
			if (cla.equalsIgnoreCase(name)) 
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
		JOptionPane.showMessageDialog(null, "Error en el archivo", "ERROR",0);
	}
	 	if (existe==false)
	 	{
	 		return -1;
	 	}
	 else 
	 	return posicion;						 							 	
  }	//cierra metodo

/*
 *Metodo por medio del cual se realiza la busqueda la cual es llamada en buscar y modificar
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
	
		RandomAccessFile miArchivo=new RandomAccessFile ("Inventario.dat","rw");
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
		JOptionPane.showMessageDialog(null, "Error en el archivo", "ERROR",0);
	}
	 if (existe==false)
	 	{
	 		return -1;
	 	}
	 else 
	 	return posicion;						 	
  }	//cierra metodo							
				
	
	public void windowClosing(WindowEvent e){
      System.exit(0);
   }
  
  /**
    * empty method
    *
    * @param e  not used
    *
    */
  public void  windowActivated(WindowEvent e){
   }

   public void windowClosed(WindowEvent e){
   }

   public void windowDeactivated(WindowEvent e){
   }

   public void windowDeiconified(WindowEvent e){
   }

   public void windowIconified(WindowEvent e){
   }

   public void windowOpened(WindowEvent e){
   }		
 }	