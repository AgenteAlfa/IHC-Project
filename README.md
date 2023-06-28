# Organizador de productividad ProTeam


## Introducción 
Basamos nuestro estudio en las desventajas que tienen los programas de manejo de organización tradicionales y conocidos frente a las limitaciones que tienen algunos usuarios al interactuar con estas aplicaciones. Como tarea principal buscamos que se pueda generar una competitividad dentro de la empresa usando el concepto de gamificación. Tenemos como funcionalidades la creación de células de trabajo, unirse a estas, fácil reconocimiento de las tareas asignadas. Como criterio principal para la decisión final de un diseño satisfactorio va a ser la satisfacción de los usuarios con la interfaz, para ello se realizarán varios prototipos. 
## Problema del estudio 
Algunos problemas que se presentan al momento de hacer una adecuada organización dentro de una empresa para el manejo de tareas, es decir que no existen las herramientas adecuadas para poder generar una competitividad dentro de la empresa sin perder el grado de unión para con la empresa. Para resolver esto, se desarrollará un aplicativo para que permita ayudar en la organización de tareas de una empresa, dicho aplicativo usará el concepto de gamificación. 
## Usuarios Potenciales 
Personas que necesiten organizar tareas dentro de una organización(dígase empresas o grupos en general)además de tener una interfaz para los comandos de voz en caso de que alguna persona desee usar todas las funcionalidades correctamente. 
Tareas a realizar por el sistema 
Nuestra aplicación busca ayudar a las empresas que necesiten mejorar su integración entre el personal sin tener la necesidad de perder competitividad entre ellos, ya que esto afectará al ambiente laboral generando en un futuro un posible bajo rendimiento de los trabajadores. La aplicación contará con una interfaz de voz para los clientes que lo necesiten. 

## Funcionalidad del sistema 
El sistema es práctico ya que ofrece una forma de controlar e impulsar el trabajo de los trabajadores, teniendo en cuenta a los usuarios potenciales a los que estamos dirigiéndonos son empresas medianas denominado como “equipo” con un alto espectro de tareas que se pueden encapsular en “grupos de trabajo” con un personal asignado.
El sistema es seguro, ya que permite el uso de la empresa mediante equipos de trabajo al que solo tendrán acceso los trabajadores mediante un código para que participen 
El sistema tiene una buena precisión para los comandos de voz usados para la activación de sus opciones, sin confundir a la interfaz de voz, además permite que las “misiones” a realizar por los “grupos de trabajo” están definidas mediante audios. 
El sistema cumple permite recompensas definidas por el “Administrador” a obtenerse mediante puntos obtenidos por los “colaboradores”, estas recompensas pueden ser varias como un dia libre, un aumento salarial, vacaciones, etc 

## Diagrama de Casos de Uso

![7c863a02-ac71-4338-bb4a-a434220489bb](https://github.com/AgenteAlfa/IHC-Project/assets/26251414/f01ba7fc-925b-49e3-b336-cc59c66a0b46)

## Requerimientos
|N°| Tipo de Req | Caso de Uso| Descripción                | Justificación          | Criterio de Cumplimiento    | Conflictos        |
|-| ------------|------------|----------------------------|------------------------|-----------------------------|-------------------|
|1| Funcional | Login | Un usuario podra loguearse | Impedir el ingreso de agentes externos| Permite ingresar al sistema | Datos incorrectos |
|2| Funcional | Registro | Un usuario podrá registrarse | Impedir el ingreso de agentes externos| Aparecer en la base de datos | Correo ya existe |
|3|No Funcional| Registro |Las contraseñas de la base de datos están encriptadas de forma automatica| Tener una mayor seguridad para evitar ataques| No poder ver las contraseñas en texto plano | No se pueden recuperar contraseñas
|4|Funcional|Nuevo Grupo|Se crea un nuevo grupo de trabajo|Con un grupo creado se puede incluir participantes y misiones|Aparición del grupo en el equipo asignado|Los grupos solo pueden ser creados por un administrador|
|5|Funcional|Nuevo Equipo|Un administrador creará un nuevo equipo|Poder manejar grupos de trabajos|Aparición en la base de datos y en la pantalla del administrador|Los equipos solo pueden ser creados por un administrador|
|6|Funcional|Asignar tareas|Un administrador colocará las tareas correspondientes a cada grupo|Para poder administrar los grupos, se necesita asignarle tareas|Aparición en el grupo designado|Las tareas pueden ser creadas por un supervisor|
|7|Funcional|Buscar un grupo|Un usuario buscará el grupo al cual unirse|Impedir el ingreso de personal que no pertenece al grupo|Generacion de codigo para ingreso y inclusion del grupo en la pantalla del usuario|Buscar códigos no existentes|
|8|Funcional|Reclamar premio|Un usuario puede reclamar un premio en función de sus puntos|Incentivar la competitividad dentro de una empresa|Notificación al administrador|No tener los puntos necesarios para el premio|
|9|No Funcional|Comandos de voz|Un usuario usa un comando de voz en vez de una función manual|Facilidad a los usuarios en la maniobra del aplicativo|inexistencia de errores en la lectura de comandos de voz|no acepta otros idiomas ni otros comandos más que los registrados|
|10|No funcional|Comando de voz|El botón de comando de voz no debe irrumpir el diseño y funcionalidad del aplicativo|Para poder tener una interfaz amigable|Las funciones no son impedidas por el uso del comando de voz|Algunos elementos son menos visibles|
|11|Funcional|Editar|Un usuario  hace cambios ya sea en un grupo o misión|Facilidad de los usuarios de solucionar errores|los datos son cambiados en la base de datos y para todos los usuarios|Cambias un campo erróneo|

## Criterios de Usabilidad 
Se aplicarán las siguientes métricas objetivas: 
1. Tiempo necesario para aprender a usar la aplicación: Este indicador medirá el tiempo que demora un usuario en aprender a usar el sistema. 
2. Número de errores al pronunciar los comandos de voz: Esta métrica mide el número de errores cometidos cuando el usuario está pronunciando los comandos de voz. 
3. Número de errores cometidos por la aplicación: Este indicador medirá la precisión con la cual la aplicación realiza sus funciones, puesto que todo sistema informático puede presentar errores de programación.
4. Mejora en los tiempos que toma completar una tarea: Este indicador medirá el tiempo que un usuario emplea para completar una tarea dada a medida que progresa usando la app 
Se aplicará las siguientes métricas subjetivas: 
1. Satisfacción con la interfaz: Esta métrica sirve para medir la satisfacción de los usuarios al usar el aplicativo. 
2. Facilidad para realizar acciones con la interfaz de voz: Este indicador mide la facilidad con la cual los usuarios realizan tareas por medio de la interfaz de voz. 
3. Placer: Disfrute de los usuarios cuando utilizan el aplicativo. 
4. Estrés: Estrés de los usuarios cuando interactúan con el aplicativo. 
5. Autopercepción de la productividad: Cuanto considera el usuario que a mejorado su productividad usando la aplicación 
6. Satisfacción con la creación, configuración y manejo de un equipo y/o grupo(s): Indica el nivel de satisfacción del usuario para realizar la correcta creación,configuración o manejo de un equipo y/o grupo de forma correcta 
## Análisis de la usabilidad 
Tomando en cuenta los criterios de usabilidad mencionados, el sistema contará con: Comandos de voz sencillos de pronunciar para no confundir a la interfaz de voz, para reducir el tiempo de ejecución de algún comando y haya un menor margen de error al pronunciarlos. 
Revisaremos el uso de las recompensas por los usuarios mostrando su interés en la sana competencia a través de un ranking Interfaz sencilla, con componentes y secciones de tamaño regular para que el usuario pueda observar a simple vista todas las funciones que ofrece el aplicativo. 
El aplicativo contará con un manual de guía y uso que se desplegará mediante un botón de ayuda, el cual indicará el uso de la interfaz así como los comandos de voz disponibles en cada etapa de la navegación por la aplicación. El manual podrá ser desplegado y cerrado en cualquier momento que el usuario necesite. 
El aplicativo contará con una señal para indicar que el sistema está escuchando al usuario y el cual el usuario podrá desactivar en cualquier momento.
## Integrantes
- BAUTISTA SANI, ARNOLD JEREMY
- OLIVIERI ROMERO, STEFANO
- SILVA GUANILO, ITALO ENRIQUE
