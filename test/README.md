# READMNE

### Documentacion
Esta aplicacion desarrollada en springboot tiene las siguientes funcionalidades:

* El endpoint POST /topsecret es capaz de devolver la ubicacion del exoplaneta y el mensaje armado con la informacion
proporcionada por los telescopios/satelites.
* El objeto de entrada debe ser de la siguiente forma:

{
    "telescopes": [
        {
            "name": "John",
            "message": [
                "programacion",
                "",
                "a",
                "objetos"
            ],
            "distance": 100,
            "location": {
                "x": 1,
                "y": 2
            }
        },
        {
            "name": "Peter",
            "message": [
                "",
                "programacion",
                "",
                "a",
                "objetos"
            ],
            "distance": 130,
            "location": {
                "x": 4,
                "y": 2
            }
        },
        {
            "name": "Smith",
            "message": [
               
                "",
                "orientada",
                "a",
                "objetos"
            ],
            "distance": 90,
            "location": {
                "x": 4,
                "y": 5
            }
        }
    ], 
    "satellites": null
}
* La respuesta obtenida incluye la posicion del exoplaneta y el mensaje armado. Tiene el siguiente formato:
{
    "position": null,
    "message": "programacion programacion a objetos"
}

* El endpoint GET /exoplaneta que devuelve el estado del exoplaneta contenida en el objeto. Ejemplo respuesta:
{
    "nombre": "Exoplanet",
    "radio": 1.2,
    "masa": "piedra",
    "gravedad": 0.98,
    "satelites": 1
}

* El endpoint PUT /exoplaneta que modifica el estado de la informacion del exoplaneta. Ejemplo request:
{
    "nombre": "Exoplanet",
    "radio": 1.5,
    "masa": "lava",
    "gravedad": 3.22,
    "satelites": 6
}
Devuelve la respuesta un OK (200) con el objeto del request

