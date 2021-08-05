# KokumoTP

Kokumo es un juego online desarrollado bajo el protocolo http para dos jugadores. Uno debera iniciar como server y el otro como cliente, a traves de su ip publica.

Reglas:

Al inicio de la partida, cada jugador deberá posicionar sus tres ninjas dentro de una cuadrícula de 5 x 5 casilleros.
En ningún momento el oponente deberá conocer las ubicaciones de las tropas enemigas; cada jugador verá dos cuadrículas:
La cuadrícula principal, en la que se hallan desplegados sus ninjas.
La cuadrícula secundaria, en la que se marcarán los ataques realizados contra su oponente.
Una vez dispuestos los guerreros en el campo de batalla, se debe decidir qué jugador iniciará la partida para, luego, cederle el turno al contrincante (y así sucesivamente).
En su turno, cada jugador podrá realizar una de las siguientes acciones por cada ninja vivo:
Moverlo 1 casillero: en cualquier dirección y con la limitante de no poder optar por esta maniobra en turnos consecutivos.
Realizar un ataque: indicar la coordenada del terreno enemigo, al cual dirigirá el ataque.
Si el ataque del oponente es dirigido hacia una casilla ocupada por un ninja, éste pierde la vida; en cambio, si está despoblada, el terreno queda intransitable.
La partida termina cuando uno de los dos jugadores elimina a la facción enemiga.
Consideraciones
Uno de los tres ninjas será el comandante del escuadrón y poseerá dos vidas.
Todo ninja puede moverse siempre y cuando el comandante siga con vida.
Pueden crearse ambientes de combate con mayor complejidad; no necesariamente tienen que ser terrenos cuadrados y pueden incorporarse otro tipo de obstáculos como ríos, piedras y árboles, con distintos efectos defensivos u ofensivos (pero esto es opcional y se sugiere explorar esta alternativa cuando el juego base esté creado).
El oponente nunca debe conocer la posición de los ninjas.
