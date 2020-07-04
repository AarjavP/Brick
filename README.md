# Brick

Brick (title in progress) is a hybrid of turn based and action elements to produce a chesslike experience in a battle royale setting. The idea is
to have a large chessboard that can support a healthy number of players, each of which try to "survive" and eliminate other players as the board slowly
decreases over time.  Naturally, the last standing player wins.  Each player will start out as single chess piece (i.e a king) and may obtain powerups spread
throughout the board, which may change their piece (and therefore experience) or even grant them a new piece to control.

As of July 2020, this game is only in the concept stage and we are currently building a very rough, but playable outline of the game.  Ideally we want this game
to have a web-based client, however this may not be possible for a first build.  Details of our current design and following classes are as outlined:


## Current Design (Planned for release by September 2020)
- Standard chessboard, supporting up to 100 players.
- Players start with one piece, may control up to two.
- Gameplay will contain turn based and action elements.  For example, players may move around asynchronously, however all pieces have cooldowns.
  - Alternatively, there will be a global timer for which all players move synchronously, however this presents issues and is for now only an idea.
- Players will spawn uniformly distributed across the board.
- A player can only eliminate another player when the latter is on cooldown (i.e they cannot move).
  -A player who is also choosing not to move will be vulnerable.
- Powerups will spawn throughout the board near players.



## Future Desires (No defined date)
- The chessboard will have obstacles preventing the player from moving to certain spots.
- Multiple chessboards will be available, mainly the geometry changes but will consider "themes."
- We want players to have a large amount of customization options for games.
- Teams.
