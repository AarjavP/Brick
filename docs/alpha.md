## First playable end-to-end version

 - 64x64 board first 20s, x60 for another 20s, x45 for 60s, x30 for 30s, x20 for 30s, x10 for 30s, x6 for 30s, x2 for 10s
 - 8 Players, can see 8 spaces out from their pieces
 - webgui
 - Everyone starts as king which can move to any adjacent spot that is in the playable area every 1 seconds
 - Random start for every one, but everyone starts at least 2 empty spaces away from other players. 
 - No teams
 - Powerups available:
   - pick up another piece (up to 2): 
     - Queen - range of 8, 1.2s cd for dist moved
     - Knight - normal chess move, 1s cd
     - Rook - range of 8, .8s for dist moved
     - Bishop - range of 8, .8s for dist moved
     - Pawn - .5s for move, .5s to turn 90deg
   - reduce cd for the piece per move by .1s
 - players can see available moves
 - game "scoreboard" - see your eliminations, players left, and time until next shrink
