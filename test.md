[OK] Given an initialized game, Then Findus is player in turn
[OK] When Findus ends its turn, Then it is Peddersen in turn
[OK] When Peddersen ends turn, its Findus turn
[OK] When game starts Then Findus should have three cards in hand.
[OK] When game starts Then Findus should have Tres at index 0, Dos at index 1, and Uno at index 2.

Tests for game functionality:
[OK] Turn counter initializes to 1.
[OK] Every time the turn changes, the turn counter increments by 1.
[OK] Findus has the first turn.
[OK] The deck has 7 cards
[OK] The deck is ordered from top to bottom: Uno, Dos, Tres, Cuatro, Cinco, Seis, Siete.
[OK] Each player starts with a deck.
[OK] When a player draws a card, it is taken from the top of their deck and removed from the deck.
[OK] When a player draws a card, it is added to the top of the hand stack.
[OK] A player cannot draw if their deck is empty.
[OK] If a player cannot draw, their hero takes 2 damage.
[] A player draws 3 cards on their first turn, and 1 card on the following turns.
[OK] When a player plays a card, it is removed from their hand.
[] When two players join game, one is selected as Findus and the other as Peddersen.
[OK] There is one hero type: Baby. Both players have type Baby -kate
[OK] Each player has a hero.
[] Hero health decreases every time its attacked by an opponent player.
[] When hero health <= 0, the hero is defeated.
[] When Findus' hero is defeated, Peddersen wins.
[] When Peddersen's hero is defeated, Findus wins.
[] Findus wins at the start of turn 8.
[OK] Peddersen and Findus take turns.
[] A player can only perform allowed actions on their turn.
[] A player can end their turn at any time.
[] At the beginning of each turn, the turn's hero is set to 3 mana.
[] A player can only play a card if they have enough mana for the card's mana cost.
[] When a player plays a card, the mana cost of the card is deducted from their mana.
[] When a player plays a card, it enters the field.
[] When a card enters the field, it occupies some position in the array of field positions.
[] If a card tries to occupy an occupied position when entering the field, the existing cards are moved up.
[] When a card enters the field, it invokes its Effect.
[] A card can only attack when it is active.
[] A card is inactive while it's in its owner's hand.
[] A card becomes active at the beginning of its owner's turn, except for the turn it is played.
[] When a card attacks, it becomes inactive.
[] When a card attacks, it deals X damage to its target, where X is the card's attack power.
[] A card can only attack the opponent hero.
[] A card can only attack opponent cards on the field.
[] When a card is dealt X damage points, it loses X health points.
[] When a card attacks another card, it takes X damage, where X is the attack value of the target card.
[] When a card's health <= 0, the card is defeated and removed from the game.

Tests for hero type Baby:
[OK] Hero health starts at 21.
[OK] Hero starts with 3 mana. -kate
[OK] Power is called "Cute". -kate
[OK] Power costs 2 mana -kate
[OK] Power deducts 2 mana from Hero when used. -kate
[OK] Power can be used if mana is more or equal to power cost -kate
[OK] Power cant be used if there isn't enough mana -kate
[OK] Power description is "Does nothing". -kate
[] Power can only be used once per turn. - kate

attribute format: (mana cost, attack, health)
Tests for each card:
-- Uno --
[OK] Has attributes: (1,1,1)

-- Dos --
[OK] Has attributes: (2,2,2)

-- Tres --
[OK] Has attributes: (3,3,3)

-- Cuatro --
[OK] Has attributes: (2,3,1)

-- Cinco --
[OK] Has attributes: (3,5,1)

-- Seis --
[OK] Has attributes: (2,1,3)

-- Siete --
[OK] Has attributes: (3,2,4)