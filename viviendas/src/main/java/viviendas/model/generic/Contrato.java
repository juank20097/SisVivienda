package viviendas.model.generic;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.List;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import viviendas.model.dao.entities.ArrMatriculado;
import viviendas.model.dao.entities.ArrPeriodo;

public class Contrato {

	public static String generarContrato(ArrMatriculado estudiante, ArrPeriodo periodo, String nombreDepartamento)
			throws FileNotFoundException, DocumentException {
		String carpetaContratos = Funciones.ruta_pdf;

		/* CONTENIDO DEL DOCUMENTO */
		String cabecera = "Comparecen a la celebraci�n del presente contrato de arrendamiento, "
				+ "por una parte, la EMPRESA P�BLICA YACHAY EP, legalmente representada por la "
				+ "Ing. Paola Katherine Soria Maldonado, Gerente Comercial, en calidad de delegada de la "
				+ "m�xima autoridad, conforme se desprende de la Resoluci�n N� YACHAY-EP-GG-2016-0010 "
				+ "de 28 de marzo de 2016, a quien en adelante se la denominar� �LA ARRENDADORA�; y, por "
				+ "otra el Sr.(ta) estudiante " + estudiante.getMatNombre().toUpperCase() + " con c�dula de "
				+ "ciudadan�a No. " + estudiante.getId().getPerDni() + "; "
				+ "por sus propios y personales derechos, a quien en adelante y para efectos de este contrato se "
				+ "denominar�, �ARRENDATARIO(A)�. Las partes son plenamente capaces para contratar y obligarse con "
				+ "el presente instrumento, al tenor de las siguientes cl�usulas:";

		String c11 = "1.1. Mediante Decreto Ejecutivo No. 1457, de 13 de marzo de 2013, publicado en el Suplemento del"
				+ " Registro Oficial No. 922, de 28 de marzo de 2013, el Presidente Constitucional de la Rep�blica del Ecuador,"
				+ " cre� la EMPRESA P�BLICA YACHAY EP, la cual tiene como objeto el desarrollo de actividades econ�micas relacionadas"
				+ " a la administraci�n del Proyecto Ciudad del Conocimiento YACHAY que, entre otras, incluye: 1. La administraci�n de "
				+ "Zonas de Especial Desarrollo Econ�mico que se creare para el efecto; 2. Administraci�n de la concesi�n y arrendamiento "
				+ "de los espacios f�sicos de las Zonas de Especial Desarrollo Econ�mico; ";

		String c12 = "1.2. En sesi�n llevada a cabo el d�a 28 de marzo de 2013, el Directorio de la EMPRESA P�BLICA YACHAY E.P., mediante Resoluci�n No. 01-DIR-YACHAY EP-28-03-2013, design� al Mgs. H�ctor Rodr�guez Ch�vez como Gerente General de la EMPRESA P�BLICA YACHAY EP.";

		String c13 = "1.3. Mediante �Acta de Entrega Recepci�n Provisional de Inmuebles�, suscrita el 30 de agosto de 2013, el Servicio de Gesti�n Inmobiliaria del Sector P�blico � INMOBILIAR- entreg� a la Empresa P�blica YACHAY EP la posesi�n del predio expropiado denominado �HACIENDA SAN JOSɔ.";

		String c14 = "1.4. Mediante �Autorizaci�n de Ocupaci�n Inmediata del bien inmueble �Hacienda San Jos� a favor de la Universidad de Investigaci�n de Tecnolog�a Experimental �Yachay��, de 15 de mayo de 2014, la Empresa P�blica YACHAY E.P. autoriza la ocupaci�n inmediata de las instalaciones edificadas en el inmueble denominado �Hacienda San Jos� a la Universidad de Investigaci�n de Tecnolog�a Experimental Yachay, para su funcionamiento; ";

		String c15 = "1.5. Con fecha 23 de octubre de 2015, la Universidad de Investigaci�n de Tecnolog�a Experimental Yachay y la Empresa P�blica YACHAY E.P suscribieron el Convenio Espec�fico Nro. 0046-2015, para la Administraci�n de Residencias Estudiantiles, Docentes y Locales Comerciales, en cuyo objeto se encarga la administraci�n de las residencias estudiantiles, de docentes, autoridades y en general los bienes inmuebles destinados a la vivienda, as� como los locales comerciales, ubicados dentro del predio de titularidad de YACHAYTECH denominado �Hacienda San Jos� a la Empresa P�blica YACHAY  EP.";

		String c16 = "1.6. Con fecha 28 de marzo del 2016 el/la Sr.(ta) " + estudiante.getMatNombre().toUpperCase()
				+ ", estudiante de la Universidad de Investigaci�n de Tecnolog�a Experimental Yachay present� su solicitud de arrendamiento de una de las residencias universitarias, la cual ha sido  aprobada.";

		String c17 = "1.7. Mediante Resoluci�n No. YACHAY EP-GG-2016-0017 de 29 de abril de 2016, el se�or Gerente General de la Empresa P�blica YACHAY E.P. deleg� al/la Gerente Comercial la suscripci�n de los contratos de arrendamiento para alojamiento con docentes, estudiantes, servidores de la Empresa P�blica �YACHAY E.P.� y y personas naturales, respecto de las residencias estudiantiles situadas en la Ciudad del Conocimiento YACHAY.";

		String c18 = "1.8. Por tratarse de un contrato de arrendamiento cuya caracter�stica de uso no es de largo plazo, �ste se regir� por los usos y costumbres de la actividad, de conformidad con el art�culo 8 de la Resoluci�n INCOP No.013-09 de 06 de marzo de 2009.";

		String c21p1 = "2.1. Son documentos habilitantes dentro del presente contrato, los siguientes:";

		String c21p2 = "a)	Documentos que acreditan la capacidad legal de los comparecientes;";

		String c21l1 = "b)	Acta de Entrega Recepci�n del inmueble, objeto de este contrato, donde describe el estado del inmueble al momento de recibir el inmueble;";

		String c21l2 = "c)	Documento/Anexo, que contiene el listado, la descripci�n y el estado de los enseres, l�nea blanca y dem�s muebles entregados al ARRENDATARIO como parte de la vivienda arrendada;";

		String c21l3 = "d)	Resoluci�n No. YACHAY EP-GG-2016-0010 de 28 de marzo de 2016, mediante la cual el Gerente General de la Empresa P�blica YACHAY EP deleg� a la Gerente Comercial la suscripci�n de los contratos de arrendamiento, entre esta Empresa P�blica y los se�ores Docentes y Estudiantes de la Universidad YACHAY.";

		String c31p1 = "3.1. Las partes de com�n acuerdo conviene en celebrar el presente Contrato de Arrendamiento  por el cual, la ARRENDADORA entrega en arrendamiento a la/el ARRENDATARIO(A), el inmueble ubicado en "
				+ nombreDepartamento
				+ ".de la Ciudad del Conocimiento YACHAY, para utilizarlo exclusivamente para vivienda, mientras sea estudiante de la Universidad de Investigaci�n de Tecnolog�a Experimental YACHAY.";

		String c31p2 = "Se deja expresa constancia que el inmueble arrendado incluye: mobiliario, enseres y l�nea blanca; cuya descripci�n, estado y caracter�sticas se encuentran detallados en el documento/anexo, que forma parte de este Contrato.";

		String c32 = "3.2. El inmueble entregado en arrendamiento, ser� destinado �nicamente para vivienda; prohibi�ndose expresamente el subarriendo del mismo.";

		String c33 = "3.3. En caso de que el estudiante sea reubicado en otra vivienda durante la vigencia del contrato, conforme la normativa y pol�ticas de la Universidad de Investigaci�n de Tecnolog�a Experimental Yachay, se suscribir� un Anexo que se agregar� a este contrato y formar� parte de �l.";

		String c41 = "4.1. El canon mensual de arrendamiento que el ARRENDATARIO pagar� a la ARRENDADORA es el valor de CINCUENTA Y CINCO D�LARES DE LOS ESTADOS UNIDOS DE NORTE AM�RICA (USD. $55,00), monto que incluye el valor de la al�cuota y los servicios b�sicos (energ�a el�ctrica, agua potable), ornato, mantenimiento entendi�ndose solamente la limpieza de espacios exteriores y comunales; y, seguridad correspondientes, siendo la �nica compensaci�n econ�mica que se pagar� por estos conceptos.";

		String c42p1 = "4.2. El valor antes mencionado ser� cancelado por el/la ARRENDATARIO(A) dentro de los diez (10) primeros d�as de cada mes, a trav�s de cualquiera de las formas descritas a continuaci�n:";

		String c42l1 = "a)	Efectivo, en las oficinas de la Gerencia Comercial  ubicadas en la Tienda Yachay Store (planta baja del Bloque de Servicios de YACHAY E.P.).";

		String c42l2 = "b)	Tarjeta de cr�dito en la tienda Yachay Store (solo cr�dito corriente), ubicada en la planta baja del Bloque de Servicios de YACHAY E.P.).";

		String c42p2 = "El ARRENDADOR deber� entregar el correspondiente comprobante de pago al ARRENDATARIO/A a fin de respaldar la transacci�n realizada. El �nico documento de constancia de pago es el RECIBO DE COBRO, el cual ser� entregado al ARRENDATARIO/A en las instalaciones de Yachay Store, una vez que se ha registrado. ";

		String c43 = "4.3. En caso de que el ARRENDATARIO(A) no cancele oportunamente los c�nones mensuales de arrendamiento, la Empresa P�blica YACHAY E.P. pondr� en conocimiento de la Universidad de Investigaci�n de Tecnolog�a Experimental YACHAY sobre este particular, a fin de que se tomen las medidas que correspondan. La Universidad de Investigaci�n de Tecnolog�a Experimental Yachay podr�, previo an�lisis del caso, negar una nueva solicitud de arrendamiento por este motivo; sin perjuicio de que la ARRENDADORA inicie las acciones administrativas y legales correspondientes para el cobro respectivo.";

		String c51p1 = "5.1.- El presente contrato entrar� en vigencia desde el "
				+ Funciones.dateToLetters(periodo.getPrdFechaInicio()) + ", y su plazo " + "concluir� el "
				+ Funciones.dateToLetters(periodo.getPrdFechaFin()) + ".";
		
		String c51p2="El proceso de CHECK IN y CHECK OUT se realizar� en los t�rminos establecidos en la Cl�usula Octava  y lo determinado en el presente Contrato de Arrendamiento.";

		String c61 = "6.1. La/el ARRENDATARIO(A) se obliga a:";

		String c61l1 = "a)	Respetar la extensi�n del espacio f�sico y cuidar los bienes muebles asignados por la ARRENDADORA;";

		String c61l2 = "b)	Mantener en buenas condiciones el inmueble arrendado, respetando el espacio f�sico asignado, cuidando los implementos descritos en el Anexo del presente Instrumento, conservando el orden y limpieza adecuados; debiendo entregar el inmueble a la culminaci�n del presente contrato, en la misma forma en la que lo recibieron a trav�s de la suscripci�n del Acta de Entrega Recepci�n;";

		String c61l3 = "c)	Se proh�be obstruir o suspender el adecuado funcionamiento de los sistemas de seguridad y sensores contra incendio;";

		String c61l4 = "d)	No consumir bebidas alcoh�licas ni sustancias estupefacientes y psicotr�picas en las instalaciones del bien arrendado ni en los espacios p�blicos de la Ciudad del Conocimiento YACHAY;";

		String c61l5 = "e)	No fumar dentro del bien arrendado, ni en espacios cerrados de la Ciudad del Conocimiento YACHAY;";

		String c61l6 = "f)	Cancelar cumplidamente el canon de arrendamiento;";

		String c61l7 = "g)	Las mejoras que impliquen cambios en la estructura interna del inmueble arrendado, deber�n ser autorizadas por escrito por la ARRENDADORA y asumidas por el ARRENDATARIO cuando estas sean desarmables; el ARRENDATARIO(A) deber� retirarlas a la finalizaci�n del contrato, sin que esto cause el detrimento del bien entregado en arrendamiento;";

		String c61l8 = "h)	Responder por sus propios actos y por los de terceros que se encontraren en el inmueble arrendado;";

		String c61l9 = "i)	No provocar algazaras o reyertas ocasionados por el ARRENDATARIO o por terceras personas que se encuentren en el interior del inmueble arrendado y/o en las inmediaciones de la Ciudad del Conocimiento YACHAY;";

		String c61l10 = "j)	Se proh�be portar, guardar o utilizar armas de fuego o artefactos explosivos y la tenencia de las mismas;";

		String c61l11 = "k)	Cumplir con lo establecido en el Reglamento de R�gimen Disciplinario, el C�digo de �tica de YACHAYTECH y dem�s disposiciones emitidas por la Universidad de Investigaci�n de Tecnolog�a Experimental Yachay y por la Empresa P�blica YACHAY E.P.; documentos/anexos que forma parte de este Contrato;";

		String c61l12 = "l)	Cumplir las leyes, reglamentos, ordenanzas, acuerdos, convenios y dem�s normativa legal vigente en el pa�s, emitidos por las entidades de control, por los Gobiernos Aut�nomos Descentralizados y por otros organismos en cumplimiento de sus funciones y competencias; ";

		String c61l13 = "m)	Notificar inmediatamente a trav�s del correo electr�nico servicios@yachay.gob.ec, los da�os en el inmueble para el arreglo o mantenimiento de los bienes e instalaciones del mismo. Ninguna solicitud o queja tendr� validez si no se la realiza por este medio;";

		String c61l14 = "n)	Nuestras cuentas y canales: Facebook y Twitter �Ciudad Yachay�, la l�nea 1800 YACHAY (922249), y el mail servicios@yachay.gob.ec son los �nicos medios autorizados para emitir comunicados oficiales referentes a la informaci�n y actividades de inter�s general, concernientes a la Ciudad del Conocimiento. Es responsabilidad, de cada habitante, mantenerse informado y hacer uso adecuado de esta informaci�n;";

		String c61l15 = "o)	Fomentar la creaci�n de una identidad ciudadana mediante la participaci�n, colaboraci�n y apoyo a las distintas campa�as, eventos y cualquier actividad generada en pro de la consolidaci�n de este objetivo; ";

		String c61l16 = "p)	Respetar la distribuci�n asignada, colaborando en los casos que por optimizaci�n de espacio deba ser reacomodado en otra residencia. En estos casos, si la nueva residencia y todo el mobiliario est� listo, la mudanza deber� realizarse en 48 horas posteriores al requerimiento;";
		
		String c61l17 = "q)	No est� permitido la tenencia de mascotas ni alimentaci�n de animales de la calle en espacios de residencias ni otros del Campus Universitario, en concordancia con la pol�tica de mascotas de la Universidad;";
		
		String c61l18 = "r)	Cubrir los costos que generen las reparaciones de los da�os ocasionados intencionalmente o por mal uso del bien producidos por el ARRENDATARIO en la vivienda y las reposiciones de los implementos descritos en el Anexo, que forma parte integrante de este Contrato; ";
		
		String c61l19 = "s)	Permitir el acceso al inmueble a los representantes de YACHAY E.P. a fin de realizar inspecciones permanentes de conservaci�n y mantenimiento del mismo con el objeto de verificar su estado. Las inspecciones se realizar�n en presencia de al menos uno de los ARRENDATARIOS (AS). De haber sido notificado el ARRENDATARIO(A) con el incumplimiento de esta u otras  disposiciones por m�s de tres ocasiones, ser� motivo suficiente para negar una futura solicitud de arrendamiento;";
		
		String c61l20 = "t)	Permitir el acceso al inmueble a los representantes de YACHAY E.P. a fin de verificar el manejo de los residuos s�lidos. En la vivienda asignada se deber� realizar la clasificaci�n de residuos s�lidos entre �org�nicos� e �inorg�nicos�, para lo cual el ARRENDATARIO(A)  deber� adquirir el o los contenedores necesarios de residuos para cumplir con lo mencionado, en un plazo de quince (15) d�as despu�s de la firma al presente contrato. La adquisici�n de contenedores es de entera responsabilidad del ARRENDATARIO(A). De haber sido notificado el ARRENDATARIO(A) con el incumplimiento de esta u otras  disposiciones por m�s de tres ocasiones, ser� motivo suficiente para negar una futura solicitud de arrendamiento;";
		
		String c61l21 = "u)	El ARRENDATARIO(A) deber� dar buen uso, mantenimiento y cuidado de las macetas y jardines en interiores y exteriores de la vivienda, en tal virtud est� prohibido arrojar cualquier tipo de residuos hacia estos espacios. De haber sido notificado el ARRENDATARIO(A) con el incumplimiento de esta u otras  disposiciones por m�s de tres ocasiones, ser� motivo suficiente para negar una futura solicitud de arrendamiento;";
		
		String c61l22 = "v)	El ARRENDATARIO(A) no podr� destinar los espacios f�sicos a otro fin distinto que el establecido en el objeto del presente contrato; ";
		
		String c61l23 = "w)	No subarrendar, ceder o transferir por cualquier concepto, el inmueble arrendado;";
		
		String c61l24 = "x)	El ARRENDATARIO(A) est� obligado a usar la vivienda arrendada seg�n los t�rminos o esp�ritu del Contrato; y no podr�n, en consecuencia, hacerla servir a otros objetos que los convenidos, o a falta de convenci�n expresa, a los que la cosa est� naturalmente destinada, o que deban presumirse, atentas las circunstancias del contrato o la costumbre del pa�s;";
		
		String c61l25 = "y)	Permitir el ingreso al inmueble a los representantes de YACHAY E.P. a fin de realizar el CHECK OUT inicial con 15 (quince) d�as de anticipaci�n a la terminaci�n del contrato, para la verificaci�n del estado del inmueble y sus bienes; el CHECK OUT final para la entrega de la vivienda, se realizar� previa notificaci�n y coordinaci�n con al menos uno de los ARRENDATARIOS, momento en el cual se suscribir� la respectiva Acta de Entrega Recepci�n de bienes;";
		
		String c61l26 = "z)	El/Los ARRENDATARIO(S) son solidariamente responsables de todas las obligaciones establecidas en el presente contrato. ";
		
		String c62 = "6.2. LA ARRENDADORA se obliga a:";

		String c62l1 = "a)	Entregar en �ptimas condiciones y debidamente equipada, la vivienda materia del presente contrato;";

		String c62l2 = "b)	Realizar, las veces que sea necesario, las reparaciones de la vivienda, que no se deban a da�os ocasionados intencionalmente o por el mal uso del bien arrendado;";

		String c62l3 = "c)	Efectuar el control y exterminio de plagas; en cuyo caso se deber� notificar al ARRENDATARIO con 24 horas de anticipaci�n a la realizaci�n de la fumigaci�n. Si dichas plagas se deben al inadecuado manejo de medidas de salubridad por parte de los ARRENDATARIOS, ser� responsabilidad de los mismos el control y exterminio de las plagas;";

		String c62l4 = "d)	Realizar inspecciones con el objeto de vigilar el adecuado mantenimiento, orden y limpieza de los bienes entregados en arrendamiento; visitas que se realizar�n contando con la presencia de al menos uno de los Arrendatarios(a); y, ";

		String c62l5 = "e)	Realizar el CHECK IN y CHECK OUT en coordinaci�n con al menos uno de los/las ARRENDATARIO(S) en los t�rminos establecidos en este contrato.";

		String c63 = "6.3. Las partes, se comprometen a suscribir el Acta Entrega Recepci�n al inicio y finalizaci�n de esta relaci�n contractual. En este documento, se detallar� el estado y las condiciones bajo las cuales se entrega y recibe el inmueble y los bienes, enseres que lo constituyen; y que forman parte del objeto de este Contrato.";

		String c64 = "6.4. El Acta Entrega Recepci�n ser� un documento habilitante para acceder al beneficio de la vivienda para el pr�ximo semestre.";
		
		String c71p1 = "7.1. El ARRENDATARIO(A) de manera expresa declara que recibe el bien inmueble, materia de este contrato de arrendamiento, en perfectas condiciones y se obliga a conservarlo de igual manera. ";

		String c71p2 = "El ARRENDATARIO(A) es responsable por los da�os provenientes por el mal uso o trato inadecuado que causare sobre el bien inmueble y enseres entregados. En caso de que no sean repuestos o reparados previo a la terminaci�n del contrato y durante el per�odo de CHECK OUT, se proceder� con las acciones legales pertinentes. ";

		String c81 = "El proceso de CHECK IN corresponde a la revisi�n y control de los bienes, inmueble y enseres, objeto del presente Contrato de Arrendamiento, y que termina con la suscripci�n de la respectiva Acta de Entrega Recepci�n. Se lo debe realizar obligatoriamente los primeros d�as de vigencia del presente contrato.";

		String c82 = "El proceso de CHECK OUT inicial corresponde a la revisi�n y control de los enseres, bienes e inmueble, objeto del presente Contrato de Arrendamiento, y se lo realizar� con 15 (quince) d�as de anticipaci�n a la finalizaci�n del presente Contrato. Este periodo determinado para la revisi�n del estado del inmueble y los bienes, es previo a la terminaci�n del contrato, estableci�ndose como un tiempo necesario para reposici�n, de ser el caso, absolver y subsanar las novedades presentadas durante el CHECK OUT inicial. El CHECK OUT finaliza con la suscripci�n del Acta de Entrega Recepci�n respectiva.";

		String c91 = "9.1. La Empresa P�blica YACHAY E.P. designa como Administrador del Contrato, al Director de Promoci�n, Servicio al Cliente y Ventas de la Gerencia Comercial de YACHAY E.P.; persona con quien el ARRENDATARIO(A), deber� canalizar y coordinar todas las obligaciones contractuales aqu� convenidas. El Administrador del Contrato ser� el encargado de velar por el fiel y debido cumplimiento de las normas legales y compromisos contractuales por parte del ARRENDATARIO(A). ";

		String c92 = "9.2. Respecto de su gesti�n reportar� a la Gerencia Administrativa Financiera de YACHAY E.P., debiendo comunicar todos los aspectos operativos, t�cnicos, econ�micos y de cualquier naturaleza que pudieren afectar al cumplimiento del objeto del contrato.";
		
		//Modificar c�digo
		
		String c101 = "10.1.- El contrato terminar� por las siguientes causales:";

		String c101l1 = "Por vencimiento del plazo;";

		String c101l2 = "Por mutuo acuerdo de las partes;";

		String c101l3 = "Por muerte del ARRENDATARIO(A) o disoluci�n de la Empresa P�blica YACHAY EP;";

		String c101l4 = "Por sentencia o laudo ejecutoriados que declaren la nulidad del contrato o la resoluci�n del mismo ha pedido de la ARRENDADORA. ";

		String c102 = "10.2.- Terminaci�n por parte de la Arrendadora: La ARRENDADORA podr� dar por terminado el arrendamiento y, por consiguiente, exigir la "
				+ "desocupaci�n y entrega del local arrendado antes de vencido el plazo legal, s�lo por una de las siguientes causas:";

		String c102l1 = "Cuando la falta de pago de las dos pensiones locativas mensuales se hubieren mantenido hasta la fecha en que se produjo la "
				+ "citaci�n de la demanda al ARRENDATARIO(A);";

		String c102l2 = "Peligro de destrucci�n o ruina del edificio en la parte que comprende el bien arrendado y que haga necesaria la reparaci�n;";

		String c102l3 = "Algazaras o reyertas ocasionadas por el ARRENDATARIO(A);";

		String c102l4 = "Incumplimiento de las obligaciones estipuladas en el presente contrato, respecto de la parte incumplida.";

		String c102l5 = "Destino del local arrendado a un objeto il�cito o distinto del convenido;";

		String c102l6 = "Subarriendo o traspaso de sus derechos, realizado por el ARRENDATARIO(A), sin tener autorizaci�n escrita para ello;";

		String c102l7 = "Ejecuci�n por el ARRENDATARIO(A) en el local arrendado de obras no autorizadas por la ARRENDADORA;";

		String c102l8 = "Resoluci�n de la ARRENDADORA de demoler el local para nueva edificaci�n. En ese caso, deber� citarse legalmente al ARRENDATARIO(A) "
				+ "con la solicitud de desahucio, con un (1) mes de anticipaci�n por lo menos, a la fecha fijada, para la demolici�n, la que s�lo podr� ser "
				+ "tramitada cuando se acompa�en los planos aprobados y el permiso de la Municipalidad respectiva para iniciar la obra.";

		String c102l9 = "Decisi�n de la ARRENDADORA de ocupar el inmueble arrendado, siempre y cuando justifique legalmente la necesidad de hacerlo, y que no "
				+ "tiene otro inmueble que ocupar. ";

		String c102l10 = "Si el ARRENDATARIO(A) no aprobara el semestre de estudios habiendo reprobado acad�micamente.";

		String c103 = "11.3.- Terminaci�n por Mutuo Acuerdo.- Cuando por circunstancias imprevistas, t�cnicas o econ�micas, o causas de fuerza mayor o caso "
				+ "fortuito, no fuere posible o conveniente para los intereses de las partes o una de ellas, continuar con el objeto de este contrato, �stas "
				+ "podr�n, por mutuo acuerdo, convenir en la extinci�n de todas o algunas de las obligaciones contractuales, en el estado que se encuentren. "
				+ "La terminaci�n por mutuo acuerdo no implicar� renuncia a derechos causados o adquiridos a favor de la ARRENDADORA o el ARRENDATARIO(A).";

		String c104 = "11.4.- Terminaci�n Unilateral.- LA ARRENDADORA podr� dar por terminado unilateral y anticipadamente el presente contrato, previa "
				+ "notificaci�n escrita al ARRENDATARIO(A) con treinta (30) d�as de anticipaci�n, sin que haya derecho a indemnizaci�n alguna a favor de la otra parte.";


		String c111 = "11.1.- Si se suscitaren divergencias o controversias en la ejecuci�n o interpretaci�n del presente contrato, las partes tratar�n en primera instancia de solucionar y llegar a un acuerdo en forma directa. De no existir dicho acuerdo, podr�n someter la controversia al proceso de mediaci�n como un sistema alternativo de soluci�n de conflictos reconocido por la Constituci�n de la Rep�blica del Ecuador y la Ley Org�nica de la Procuradur�a General del Estado; para lo cual las partes estipulan acudir a uno de los Centros de Mediaci�n de la Funci�n Judicial en Imbabura.";

		String c112 = "11.2.- Por el contrario, si las partes no llegaren a un acuerdo, las controversias deber�n sustanciarse ante la Unidad Judicial de lo Contencioso Administrativo con competencia para la provincia de Imbabura, observando lo previsto en la Ley de la materia.";
		
		String c121 = "12.1. La legislaci�n aplicable a este Contrato es exclusivamente la ecuatoriana. En todo caso, el ARRENDATARIO(A) renuncia a utilizar la v�a diplom�tica para todo reclamo relacionado con este contrato. Si el ARRENDATARIO(A) incumplieren este compromiso, la ARRENDADORA podr� dar por terminado unilateralmente el contrato.";

		String c122 = "12.2.- Este contrato, al ser un predio rural estar� regulado de conformidad con lo prescrito en el C�digo Civil, y en lo que fuere aplicable bajo la Ley de Inquilinato.";

		String c131 = "13.1. A efectos de cualquier aviso o notificaci�n por escrito, las partes se�alan como su domicilio las siguientes direcciones: ";
		
		String c132 = "13.2.- Cualquier cambio de direcci�n deber� ser notificado por escrito a la otra parte para que surta sus efectos legales, de lo contrario tendr�n validez los avisos efectuados a las direcciones antes indicadas.  En caso de no constar la direcci�n del ARRENDATARIO(A), la ARRENDADORA tramitar� directamente con la Universidad de Tecnolog�a Experimental Yachay la notificaci�n de las comunicaciones que les corresponda.";

		String c133 = "13.3.- Las controversias deben tramitarse de conformidad con la Cl�usula D�cima Segunda de este contrato.";

		String c141p1 = "14.1.- Libre y voluntariamente, previo el cumplimiento de los requisitos exigidos por las leyes de la materia. Las partes declaran expresamente su aceptaci�n a todo lo convenido en el presente Contrato de Arrendamiento, a cuyas estipulaciones se someten.";

		String c141p2 = "Para constancia de lo estipulado, las partes firman tres (3) ejemplares de igual valor, contenido y efecto jur�dico.";

		/* ESTRUCTURA DEL DOCUMENTO */
		Document documento = new Document();

		PdfWriter.getInstance(documento, new FileOutputStream(
				carpetaContratos + periodo.getPrdId() + "_" + estudiante.getId().getPerDni() + ".pdf"));

		documento.setPageSize(PageSize.A4);
		documento.setMargins(45, 45, 45, 45); // (float marginLeft, float
												// marginRight, float marginTop,
												// float marginBottom)

		Font titulo = FontFactory.getFont(FontFactory.TIMES_ROMAN, 12, Font.BOLD);
		Font subtitulo = FontFactory.getFont(FontFactory.TIMES_ROMAN, 10, Font.BOLD);
		Font parrafo = FontFactory.getFont(FontFactory.TIMES_ROMAN, 10, Font.NORMAL);

		Paragraph pTitulo = new Paragraph("CONTRATO DE ARRENDAMIENTO", titulo);
		pTitulo.setAlignment(Element.ALIGN_CENTER);

		Paragraph pCabecera = new Paragraph(cabecera, parrafo);
		pCabecera.setAlignment(Element.ALIGN_JUSTIFIED);

		Paragraph pC11 = new Paragraph(c11, parrafo);
		pC11.setAlignment(Element.ALIGN_JUSTIFIED);

		Paragraph pC12 = new Paragraph(c12, parrafo);
		pC12.setAlignment(Element.ALIGN_JUSTIFIED);

		Paragraph pC13 = new Paragraph(c13, parrafo);
		pC13.setAlignment(Element.ALIGN_JUSTIFIED);

		Paragraph pC14 = new Paragraph(c14, parrafo);
		pC14.setAlignment(Element.ALIGN_JUSTIFIED);

		Paragraph pC15 = new Paragraph(c15, parrafo);
		pC15.setAlignment(Element.ALIGN_JUSTIFIED);

		Paragraph pC16 = new Paragraph(c16, parrafo);
		pC16.setAlignment(Element.ALIGN_JUSTIFIED);

		Paragraph pC17 = new Paragraph(c17, parrafo);
		pC17.setAlignment(Element.ALIGN_JUSTIFIED);

		Paragraph pC18 = new Paragraph(c18, parrafo);
		pC18.setAlignment(Element.ALIGN_JUSTIFIED);

		Paragraph pC21p1 = new Paragraph(c21p1, parrafo);
		pC21p1.setAlignment(Element.ALIGN_JUSTIFIED);

		Paragraph pC21p2 = new Paragraph(c21p2, parrafo);
		pC21p2.setAlignment(Element.ALIGN_JUSTIFIED);

		Paragraph pC21l1 = new Paragraph(c21l1, parrafo);
		pC21l1.setAlignment(Element.ALIGN_JUSTIFIED);

		Paragraph pC21l2 = new Paragraph(c21l2, parrafo);
		pC21l2.setAlignment(Element.ALIGN_JUSTIFIED);

		Paragraph pC21l3 = new Paragraph(c21l3, parrafo);
		pC21l3.setAlignment(Element.ALIGN_JUSTIFIED);

		List lc21 = new List(true, true);
		lc21.setLowercase(true);
		lc21.setIndentationLeft(20);
		lc21.setIndentationLeft(20);
		lc21.add(new ListItem(pC21l1));
		lc21.add(new ListItem(pC21l2));
		lc21.add(new ListItem(pC21l3));

		Paragraph pC31p1 = new Paragraph(c31p1, parrafo);
		pC31p1.setAlignment(Element.ALIGN_JUSTIFIED);
		
		Paragraph pC31p2 = new Paragraph(c31p2, parrafo);
		pC31p2.setAlignment(Element.ALIGN_JUSTIFIED);

		Paragraph pC32 = new Paragraph(c32, parrafo);
		pC32.setAlignment(Element.ALIGN_JUSTIFIED);
		
		Paragraph pC33 = new Paragraph(c33, parrafo);
		pC33.setAlignment(Element.ALIGN_JUSTIFIED);

		Paragraph pC41 = new Paragraph(c41, parrafo);
		pC41.setAlignment(Element.ALIGN_JUSTIFIED);

		Paragraph pC42p1 = new Paragraph(c42p1, parrafo);
		pC42p1.setAlignment(Element.ALIGN_JUSTIFIED);

		Paragraph pC42l1 = new Paragraph(c42l1, parrafo);
		pC42l1.setAlignment(Element.ALIGN_JUSTIFIED);

		Paragraph pC42l2 = new Paragraph(c42l2, parrafo);
		pC42l2.setAlignment(Element.ALIGN_JUSTIFIED);

		List lc42 = new List();
		lc42.setIndentationLeft(20);
		lc42.add(new ListItem(pC42l1));
		lc42.add(new ListItem(pC42l2));

		Paragraph pC42p2 = new Paragraph(c42p2, parrafo);
		pC42p2.setAlignment(Element.ALIGN_JUSTIFIED);

		Paragraph pC43 = new Paragraph(c43, parrafo);
		pC43.setAlignment(Element.ALIGN_JUSTIFIED);

		Paragraph pC51p1 = new Paragraph(c51p1, parrafo);
		pC51p1.setAlignment(Element.ALIGN_JUSTIFIED);
		
		Paragraph pC51p2 = new Paragraph(c51p2, parrafo);
		pC51p2.setAlignment(Element.ALIGN_JUSTIFIED);

		Paragraph pC61 = new Paragraph(c61, subtitulo);
		pC61.setAlignment(Element.ALIGN_JUSTIFIED);

		Paragraph pC61l1 = new Paragraph(c61l1, parrafo);
		pC61l1.setAlignment(Element.ALIGN_JUSTIFIED);

		Paragraph pC61l2 = new Paragraph(c61l2, parrafo);
		pC61l2.setAlignment(Element.ALIGN_JUSTIFIED);

		Paragraph pC61l3 = new Paragraph(c61l3, parrafo);
		pC61l3.setAlignment(Element.ALIGN_JUSTIFIED);

		Paragraph pC61l4 = new Paragraph(c61l4, parrafo);
		pC61l4.setAlignment(Element.ALIGN_JUSTIFIED);

		Paragraph pC61l5 = new Paragraph(c61l5, parrafo);
		pC61l5.setAlignment(Element.ALIGN_JUSTIFIED);

		Paragraph pC61l6 = new Paragraph(c61l6, parrafo);
		pC61l6.setAlignment(Element.ALIGN_JUSTIFIED);

		Paragraph pC61l7 = new Paragraph(c61l7, parrafo);
		pC61l7.setAlignment(Element.ALIGN_JUSTIFIED);

		Paragraph pC61l8 = new Paragraph(c61l8, parrafo);
		pC61l8.setAlignment(Element.ALIGN_JUSTIFIED);

		Paragraph pC61l9 = new Paragraph(c61l9, parrafo);
		pC61l9.setAlignment(Element.ALIGN_JUSTIFIED);

		Paragraph pC61l10 = new Paragraph(c61l10, parrafo);
		pC61l10.setAlignment(Element.ALIGN_JUSTIFIED);

		Paragraph pC61l11 = new Paragraph(c61l11, parrafo);
		pC61l11.setAlignment(Element.ALIGN_JUSTIFIED);

		Paragraph pC61l12 = new Paragraph(c61l12, parrafo);
		pC61l12.setAlignment(Element.ALIGN_JUSTIFIED);

		Paragraph pC61l13 = new Paragraph(c61l13, parrafo);
		pC61l13.setAlignment(Element.ALIGN_JUSTIFIED);

		Paragraph pC61l14 = new Paragraph(c61l14, parrafo);
		pC61l14.setAlignment(Element.ALIGN_JUSTIFIED);

		Paragraph pC61l15 = new Paragraph(c61l15, parrafo);
		pC61l15.setAlignment(Element.ALIGN_JUSTIFIED);
		
		Paragraph pC61l16 = new Paragraph(c61l16, parrafo);
		pC61l16.setAlignment(Element.ALIGN_JUSTIFIED);
		
		Paragraph pC61l17 = new Paragraph(c61l17, parrafo);
		pC61l17.setAlignment(Element.ALIGN_JUSTIFIED);
		
		Paragraph pC61l18 = new Paragraph(c61l18, parrafo);
		pC61l18.setAlignment(Element.ALIGN_JUSTIFIED);
		
		Paragraph pC61l19 = new Paragraph(c61l19, parrafo);
		pC61l19.setAlignment(Element.ALIGN_JUSTIFIED);
		
		Paragraph pC61l20 = new Paragraph(c61l20, parrafo);
		pC61l20.setAlignment(Element.ALIGN_JUSTIFIED);
		
		Paragraph pC61l21 = new Paragraph(c61l21, parrafo);
		pC61l21.setAlignment(Element.ALIGN_JUSTIFIED);
		
		Paragraph pC61l22 = new Paragraph(c61l22, parrafo);
		pC61l22.setAlignment(Element.ALIGN_JUSTIFIED);
		
		Paragraph pC61l23 = new Paragraph(c61l23, parrafo);
		pC61l23.setAlignment(Element.ALIGN_JUSTIFIED);
		
		Paragraph pC61l24 = new Paragraph(c61l24, parrafo);
		pC61l24.setAlignment(Element.ALIGN_JUSTIFIED);
		
		Paragraph pC61l25 = new Paragraph(c61l25, parrafo);
		pC61l25.setAlignment(Element.ALIGN_JUSTIFIED);
		
		Paragraph pC61l26 = new Paragraph(c61l26, parrafo);
		pC61l26.setAlignment(Element.ALIGN_JUSTIFIED);

		List lc61 = new List(true, true);
		lc61.setLowercase(true);
		lc61.setIndentationLeft(30);
		lc61.add(new ListItem(pC61l1));
		lc61.add(new ListItem(pC61l2));
		lc61.add(new ListItem(pC61l3));
		lc61.add(new ListItem(pC61l4));
		lc61.add(new ListItem(pC61l5));
		lc61.add(new ListItem(pC61l6));
		lc61.add(new ListItem(pC61l7));
		lc61.add(new ListItem(pC61l8));
		lc61.add(new ListItem(pC61l9));
		lc61.add(new ListItem(pC61l10));
		lc61.add(new ListItem(pC61l11));
		lc61.add(new ListItem(pC61l12));
		lc61.add(new ListItem(pC61l13));
		lc61.add(new ListItem(pC61l14));
		lc61.add(new ListItem(pC61l15));
		lc61.add(new ListItem(pC61l16));
		lc61.add(new ListItem(pC61l17));
		lc61.add(new ListItem(pC61l18));
		lc61.add(new ListItem(pC61l19));
		lc61.add(new ListItem(pC61l20));
		lc61.add(new ListItem(pC61l21));
		lc61.add(new ListItem(pC61l22));
		lc61.add(new ListItem(pC61l23));
		lc61.add(new ListItem(pC61l24));
		lc61.add(new ListItem(pC61l25));
		lc61.add(new ListItem(pC61l26));

		Paragraph pC62 = new Paragraph(c62, subtitulo);
		pC62.setAlignment(Element.ALIGN_JUSTIFIED);

		Paragraph pC62l1 = new Paragraph(c62l1, parrafo);
		pC62l1.setAlignment(Element.ALIGN_JUSTIFIED);

		Paragraph pC62l2 = new Paragraph(c62l2, parrafo);
		pC62l2.setAlignment(Element.ALIGN_JUSTIFIED);

		Paragraph pC62l3 = new Paragraph(c62l3, parrafo);
		pC62l3.setAlignment(Element.ALIGN_JUSTIFIED);

		Paragraph pC62l4 = new Paragraph(c62l4, parrafo);
		pC62l4.setAlignment(Element.ALIGN_JUSTIFIED);

		Paragraph pC62l5 = new Paragraph(c62l5, parrafo);
		pC62l5.setAlignment(Element.ALIGN_JUSTIFIED);

		List lc62 = new List(true, true);
		lc62.setLowercase(true);
		lc62.setIndentationLeft(20);
		lc62.add(new ListItem(pC62l1));
		lc62.add(new ListItem(pC62l2));
		lc62.add(new ListItem(pC62l3));
		lc62.add(new ListItem(pC62l4));
		lc62.add(new ListItem(pC62l5));

		Paragraph pC63 = new Paragraph(c63, subtitulo);
		pC63.setAlignment(Element.ALIGN_JUSTIFIED);
		
		Paragraph pC64 = new Paragraph(c64, subtitulo);
		pC64.setAlignment(Element.ALIGN_JUSTIFIED);

		Paragraph pC71p1 = new Paragraph(c71p1, parrafo);
		pC71p1.setAlignment(Element.ALIGN_JUSTIFIED);
		
		Paragraph pC71p2 = new Paragraph(c71p2, parrafo);
		pC71p2.setAlignment(Element.ALIGN_JUSTIFIED);

		Paragraph pC81 = new Paragraph(c81, parrafo);
		pC81.setAlignment(Element.ALIGN_JUSTIFIED);

		Paragraph pC82 = new Paragraph(c82, parrafo);
		pC82.setAlignment(Element.ALIGN_JUSTIFIED);

		Paragraph pC91 = new Paragraph(c91, parrafo);
		pC91.setAlignment(Element.ALIGN_JUSTIFIED);
		
		Paragraph pC92 = new Paragraph(c92, parrafo);
		pC92.setAlignment(Element.ALIGN_JUSTIFIED);

		//Modificar C�digo
		
		Paragraph pC101 = new Paragraph(c101, parrafo);
		pC101.setAlignment(Element.ALIGN_JUSTIFIED);

		Paragraph pC101l1 = new Paragraph(c101l1, parrafo);
		pC101l1.setAlignment(Element.ALIGN_JUSTIFIED);

		Paragraph pC101l2 = new Paragraph(c101l2, parrafo);
		pC101l2.setAlignment(Element.ALIGN_JUSTIFIED);

		Paragraph pC101l3 = new Paragraph(c101l3, parrafo);
		pC101l3.setAlignment(Element.ALIGN_JUSTIFIED);

		Paragraph pC101l4 = new Paragraph(c101l4, parrafo);
		pC101l4.setAlignment(Element.ALIGN_JUSTIFIED);

		List lc101 = new List(true, true);
		lc101.setLowercase(true);
		lc101.setIndentationLeft(20);
		lc101.add(new ListItem(pC101l1));
		lc101.add(new ListItem(pC101l2));
		lc101.add(new ListItem(pC101l3));
		lc101.add(new ListItem(pC101l4));

		Paragraph pC102 = new Paragraph(c102, parrafo);
		pC102.setAlignment(Element.ALIGN_JUSTIFIED);

		Paragraph pC102l1 = new Paragraph(c102l1, parrafo);
		pC102l1.setAlignment(Element.ALIGN_JUSTIFIED);

		Paragraph pC102l2 = new Paragraph(c102l2, parrafo);
		pC102l2.setAlignment(Element.ALIGN_JUSTIFIED);

		Paragraph pC102l3 = new Paragraph(c102l3, parrafo);
		pC102l3.setAlignment(Element.ALIGN_JUSTIFIED);

		Paragraph pC102l4 = new Paragraph(c102l4, parrafo);
		pC102l4.setAlignment(Element.ALIGN_JUSTIFIED);

		Paragraph pC102l5 = new Paragraph(c102l5, parrafo);
		pC102l5.setAlignment(Element.ALIGN_JUSTIFIED);

		Paragraph pC102l6 = new Paragraph(c102l6, parrafo);
		pC102l6.setAlignment(Element.ALIGN_JUSTIFIED);

		Paragraph pC102l7 = new Paragraph(c102l7, parrafo);
		pC102l7.setAlignment(Element.ALIGN_JUSTIFIED);

		Paragraph pC102l8 = new Paragraph(c102l8, parrafo);
		pC102l8.setAlignment(Element.ALIGN_JUSTIFIED);

		Paragraph pC102l9 = new Paragraph(c102l9, parrafo);
		pC102l9.setAlignment(Element.ALIGN_JUSTIFIED);

		Paragraph pC102l10 = new Paragraph(c102l10, parrafo);
		pC102l10.setAlignment(Element.ALIGN_JUSTIFIED);

		List lc102 = new List(true, true);
		lc102.setLowercase(true);
		lc102.setIndentationLeft(20);
		lc102.add(new ListItem(pC102l1));
		lc102.add(new ListItem(pC102l2));
		lc102.add(new ListItem(pC102l3));
		lc102.add(new ListItem(pC102l4));
		lc102.add(new ListItem(pC102l5));
		lc102.add(new ListItem(pC102l6));
		lc102.add(new ListItem(pC102l7));
		lc102.add(new ListItem(pC102l8));
		lc102.add(new ListItem(pC102l9));
		lc102.add(new ListItem(pC102l10));

		Paragraph pC103 = new Paragraph(c103, parrafo);
		pC103.setAlignment(Element.ALIGN_JUSTIFIED);

		Paragraph pC104 = new Paragraph(c104, parrafo);
		pC104.setAlignment(Element.ALIGN_JUSTIFIED);
		
		Paragraph pC111 = new Paragraph(c111, parrafo);
		pC111.setAlignment(Element.ALIGN_JUSTIFIED);
		
		Paragraph pC112 = new Paragraph(c112, parrafo);
		pC112.setAlignment(Element.ALIGN_JUSTIFIED);

		Paragraph pC121 = new Paragraph(c121, parrafo);
		pC121.setAlignment(Element.ALIGN_JUSTIFIED);

		Paragraph pC122 = new Paragraph(c122, parrafo);
		pC122.setAlignment(Element.ALIGN_JUSTIFIED);

		Paragraph pC131 = new Paragraph(c131, parrafo);
		pC131.setAlignment(Element.ALIGN_JUSTIFIED);

		Paragraph pC132 = new Paragraph(c132, parrafo);
		pC132.setAlignment(Element.ALIGN_JUSTIFIED);

		Paragraph pC133 = new Paragraph(c133, parrafo);
		pC133.setAlignment(Element.ALIGN_JUSTIFIED);

		Paragraph pC141p1 = new Paragraph(c141p1, parrafo);
		pC141p1.setAlignment(Element.ALIGN_JUSTIFIED);

		Paragraph pC141p2 = new Paragraph(c141p2, parrafo);
		pC141p2.setAlignment(Element.ALIGN_JUSTIFIED);

		PdfPCell t1c1f1 = new PdfPCell(new Phrase("LA ARRENDADORA:", subtitulo));
		t1c1f1.setBorder(Rectangle.NO_BORDER);

		PdfPCell t1c2f1 = new PdfPCell(new Phrase("EL ARRENDADOR(A):",
				subtitulo));
		t1c2f1.setBorder(Rectangle.NO_BORDER);

		PdfPCell t2c1f1 = new PdfPCell(new Phrase("PAOLA SORIA MALDONADO ",
				parrafo));
		t2c1f1.setBorder(Rectangle.NO_BORDER);

		PdfPCell t2c2f1 = new PdfPCell(new Phrase(estudiante.getMatNombre()
				.toUpperCase(), parrafo));
		t2c2f1.setBorder(Rectangle.NO_BORDER);

		PdfPCell t2c1f2 = new PdfPCell(new Phrase("GERENTE COMERCIAL (E)",
				parrafo));
		t2c1f2.setBorder(Rectangle.NO_BORDER);

		PdfPCell t2c2f2 = new PdfPCell(new Phrase("C.C. "
				+ estudiante.getId().getPerDni(), parrafo));
		t2c2f2.setBorder(Rectangle.NO_BORDER);

		PdfPCell t2c1f3 = new PdfPCell(new Phrase("EMPRESA P�BLICA YACHAY EP",
				subtitulo));
		t2c1f3.setBorder(Rectangle.NO_BORDER);

		PdfPCell vacia = new PdfPCell(new Phrase("	", parrafo));
		vacia.setBorder(Rectangle.NO_BORDER);

		PdfPTable tFirmas = new PdfPTable(3);
		tFirmas.setWidthPercentage(100);
		tFirmas.addCell(t1c1f1);
		tFirmas.addCell(vacia);
		tFirmas.addCell(t1c2f1);

		PdfPTable firmas = new PdfPTable(3);
		firmas.setWidthPercentage(100);
		firmas.addCell(t2c1f1);
		firmas.addCell(vacia);
		firmas.addCell(t2c2f1);
		firmas.addCell(t2c1f2);
		firmas.addCell(vacia);
		firmas.addCell(t2c2f2);
		firmas.addCell(t2c1f3);
		firmas.addCell(vacia);
		firmas.addCell(vacia);

		/* ESCRITURA DEL DOCUMENTO */
		documento.open();
		documento.add(pTitulo);
		documento.add(Chunk.NEWLINE);
		documento.add(pCabecera);
		documento.add(Chunk.NEWLINE);
		documento.add(new Paragraph("CL�USULA PRIMERA.- ANTECEDENTES:", subtitulo));
		documento.add(pC11);
		documento.add(Chunk.NEWLINE);
		documento.add(pC12);
		documento.add(Chunk.NEWLINE);
		documento.add(pC13);
		documento.add(Chunk.NEWLINE);
		documento.add(pC14);
		documento.add(Chunk.NEWLINE);
		documento.add(pC15);
		documento.add(Chunk.NEWLINE);
		documento.add(pC16);
		documento.add(Chunk.NEWLINE);
		documento.add(pC17);
		documento.add(Chunk.NEWLINE);
		documento.add(pC18);
		documento.add(Chunk.NEWLINE);
		documento.add(Chunk.NEWLINE);
		documento.add(new Paragraph("CL�USULA SEGUNDA.- DOCUMENTOS HABILITANTES:", subtitulo));
		documento.add(pC21p1);
		documento.add(Chunk.NEWLINE);
		documento.add(pC21p2);
		documento.add(Chunk.NEWLINE);
		documento.add(lc21);
		documento.add(Chunk.NEWLINE);
		documento.add(new Paragraph("CL�USULA TERCERA.- OBJETO DEL CONTRATO DE ARRENDAMIENTO:", subtitulo));
		documento.add(pC31p1);
		documento.add(Chunk.NEWLINE);
		documento.add(pC31p2);
		documento.add(Chunk.NEWLINE);
		documento.add(pC32);
		documento.add(Chunk.NEWLINE);
		documento.add(pC33);
		documento.add(Chunk.NEWLINE);
		documento.add(new Paragraph("CL�USULA CUARTA.- CANON MENSUAL DE ARRENDAMIENTO Y FORMA DE PAGO:", subtitulo));
		documento.add(pC41);
		documento.add(Chunk.NEWLINE);
		documento.add(pC42p1);
		documento.add(lc42);
		documento.add(Chunk.NEWLINE);
		documento.add(pC42p2);
		documento.add(Chunk.NEWLINE);
		documento.add(pC43);
		documento.add(Chunk.NEWLINE);
		documento.add(new Paragraph("CL�USULA QUINTA.- PLAZO:", subtitulo));
		documento.add(pC51p1);
		documento.add(Chunk.NEWLINE);
		documento.add(pC51p2);
		documento.add(Chunk.NEWLINE);
		documento.add(new Paragraph("CL�USULA SEXTA.- OBLIGACIONES DE LAS PARTES:", subtitulo));
		documento.add(pC61);
		documento.add(lc61);
		documento.add(Chunk.NEWLINE);
		documento.add(pC62);
		documento.add(lc62);
		documento.add(Chunk.NEWLINE);
		documento.add(pC63);
		documento.add(Chunk.NEWLINE);
		documento.add(pC64);
		documento.add(Chunk.NEWLINE);
		documento.add(new Paragraph("CL�USULA S�PTIMA.- NORMAS DE SEGURIDAD:", subtitulo));
		documento.add(pC71p1);
		documento.add(Chunk.NEWLINE);
		documento.add(pC71p2);
		documento.add(Chunk.NEWLINE);
		documento.add(new Paragraph("CL�USULA OCTAVA.- FACULTADES DE LAS PARTES:", subtitulo));
		documento.add(pC81);
		documento.add(Chunk.NEWLINE);
		documento.add(pC82);
		documento.add(Chunk.NEWLINE);
		documento.add(new Paragraph("CL�USULA NOVENA.- DECLARACI�N DEL ESTADO DEL INMUEBLE:", subtitulo));
		documento.add(pC91);
		documento.add(Chunk.NEWLINE);
		documento.add(pC92);
		documento.add(Chunk.NEWLINE);
		//Modificar C�digo
		documento.add(new Paragraph(
				"CL�USULA D�CIMA.- TERMINACI�N DEL CONTRATO:",
				subtitulo));
		documento.add(pC101);
		documento.add(Chunk.NEWLINE);
		documento.add(lc101);
		documento.add(Chunk.NEWLINE);
		documento.add(pC102);
		documento.add(Chunk.NEWLINE);
		documento.add(lc102);
		documento.add(Chunk.NEWLINE);
		documento.add(pC103);
		documento.add(Chunk.NEWLINE);
		documento.add(pC104);
		documento.add(Chunk.NEWLINE);
		documento.add(new Paragraph(
				"CL�USULA D�CIMA PRIMERA.- SOLUCI�N DE CONTROVERSIAS:", subtitulo));
		documento.add(pC111);
		documento.add(Chunk.NEWLINE);
		documento.add(pC112);
		documento.add(Chunk.NEWLINE);
		documento.add(new Paragraph(
				"CL�USULA D�CIMO SEGUNDA.- SOLUCI�N DE CONTROVERSIAS:",
				subtitulo));
		documento.add(pC121);
		documento.add(Chunk.NEWLINE);
		documento.add(pC122);
		documento.add(Chunk.NEWLINE);
		documento.add(new Paragraph(
				"CL�USULA D�CIMO TERCERA.- NOTIFICACIONES:", subtitulo));
		documento.add(pC131);
		documento.add(Chunk.NEWLINE);
		documento.add(new Paragraph("LA ARRENDADORA:", subtitulo));
		documento.add(new Paragraph("Director de Promoci�n, Servicio al Cliente y Ventas",parrafo));
		documento.add(new Paragraph("AMAZONAS N26-146 Y LA NI�A.", parrafo));
		documento.add(new Paragraph("QUITO (PICHINCHA) � ECUADOR", parrafo));
		documento.add(new Paragraph("TEL�FONO: 3949100", parrafo));
		documento.add(Chunk.NEWLINE);
		documento.add(new Paragraph("EL ARRENDATARIO(A):", subtitulo));
		documento.add(new Paragraph("NOMBRE DEL ESTUDIANTE:", subtitulo));
		documento.add(new Paragraph(estudiante.getMatNombre().toUpperCase(),parrafo));
		documento.add(Chunk.NEWLINE);
		documento.add(new Paragraph("TEL�FONO:............................", subtitulo));
		documento.add(Chunk.NEWLINE);
		documento.add(new Paragraph("MAIL:", subtitulo));
		documento.add(new Paragraph(estudiante.getMatCorreo(),parrafo));
		documento.add(new Paragraph(estudiante.getMatCorreoIns(),parrafo));
		documento.add(Chunk.NEWLINE);
		documento.add(new Paragraph("CIUDAD DEL CONOCIMIENTO YACHAY, PROYECTO YACHAY", parrafo));
		documento.add(new Paragraph("URCUQU� (IMBABURA) � ECUADOR", parrafo));
		documento.add(Chunk.NEWLINE);
		documento.add(Chunk.NEWLINE);
		documento.add(pC132);
		documento.add(Chunk.NEWLINE);
		documento.add(pC133);
		documento.add(Chunk.NEWLINE);
		documento
				.add(new Paragraph(
						"CL�USULA D�CIMO CUARTA.- ACEPTACI�N DE LAS PARTES:",
						subtitulo));
		documento.add(pC141p1);
		documento.add(pC141p2);
		documento.add(Chunk.NEWLINE);
		documento.add(Chunk.NEWLINE);
		documento.add(tFirmas);
		documento.add(Chunk.NEWLINE);
		documento.add(Chunk.NEWLINE);
		documento.add(Chunk.NEWLINE);
		documento.add(firmas);
		documento.close();

		return periodo.getPrdId() + "_" + estudiante.getId().getPerDni()
				+ ".pdf";
	}
}
