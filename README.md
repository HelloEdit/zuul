<div align="center">
    <h1>Zuul</h1>
</div>

This is my Zuul game made for the IPO unit (IGI-1104) under the supervision of Denis Bureau.

**Notes:** Although the game logic code (Game, Command, Room...) is interface agnostic, some properties are defined as
Observables from JavaFX. The reason for this is that in order to update the display dynamically, you have to listen to
changes made to them, which is not possible with vanilla Java.
