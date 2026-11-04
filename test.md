Tests for game functionality:
Turn taking
[OK] Given an initialized game, Then Findus is player in turn
[OK] When Findus ends its turn, Then it is Peddersen in turn
[OK] When Peddersen ends turn, its Findus turn
[OK] At the beginning of each turn, the turn's hero is set to 3 mana.
[] A player can end their turn at any time.

Game-state at start of game
[OK] When game starts Then players should have drawn three cards in hand.
[OK] When game starts Then players should have Tres at index 0, Dos at index 1, and Uno at index 2.
[OK] Turn counter initializes to 1.
[OK] Every time the turn changes, the turn counter increments by 1.
[OK] The deck has 7 cards. 
[OK] The deck is ordered from top to bottom: Uno, Dos, Tres, Cuatro, Cinco, Seis, Siete.
[OK] Each player starts with a deck.
[OK] Each player has a hero.
[OK] There is one hero type: Baby. Both players have type Baby

Cards and deck
[OK] When a player draws a card, it is taken from the top of their deck and removed from the deck.
[OK] When a player draws a card, it is added to the top of the hand stack.
[OK] A player cannot draw if their deck is empty.
[OK] If a player cannot draw, their hero takes 2 damage.
[OK] A card is inactive while it's in its owner' hand

Playing cards and the Field
[OK] When a player plays a card, it is removed from their hand.
[OK] A player can only play a card if they have enough mana for the card's mana cost.
[OK] When a player plays a card, the mana cost of the card is deducted from their mana.
[OK] When a player plays a card, it enters the field.
[] When a card enters the field, it invokes its Effect. - Not alphastone
[OK] A card becomes active at the beginning of its owner's turn, except for the turn it is played.
[OK] A card should not go active on turn its played.

Attacks
[OK] A card can only attack when it is active.
[OK] When a card attacks, it becomes inactive.
[OK] When a card attacks, it deals X damage to its target (hero or card), where X is the card's attack power.
[OK] When a card is dealt X damage points, it loses X health points.
[OK] When a card attacks another card, it takes X damage, where X is the attack value of the target card.
[OK] When a card's health <= 0, the card is defeated and removed from the field and from the game.

Defeat and victory
[OK] Findus wins at the start of turn 8.
[OK] When hero health <= 0, the hero is defeated.

Other Functionalities
[OK] A player can only perform allowed actions on their turn.

Tests for hero type Baby:
[OK] Hero health starts at 21.
[OK] Hero starts with 3 mana. 
[OK] Power is called "Cute". 
[OK] Power costs 2 mana 
[OK] Power deducts 2 mana from Hero when used. 
[OK] Power can be used if mana is more or equal to power cost 
[OK] Power cant be used if there isn't enough mana 
[OK] Power description is "Does nothing". 
[OK] Power can only be used once per turn. 
[OK] Power is reset every round 

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

All 3 are identical to alphastone, except following changes: 

BetaStone:
[OK] Player mana should equal number of rounds.
[OK] Max val of player mana is 7.
[OK] Winner is the player that decreases opponent health to zero or less.

GammaStone:
[OK] Findus has hero type ThaiChef.
[OK] Pedderson has hero type DanishChef.
[OK] Hero thaiChef has power named Chili.
[OK] Chili power decreases opponent health by 2.
[OK] Chili has description. "Deal 2 damage to opponent hero."
[OK] DanishChef has power named "Sovs".
[OK] Sovs adds a card to field.
[OK] Sovs card has 1 attack 1 health.
[OK] Sovs has description "Summon Sovs card."
[OK] Winner is the player that first clears the other persons field. 
[OK] Player can only win after round 3.
[OK] If both fields are empty after round 3, Pedderson wins. 

DeltaStone: (identical to alphastone).
[OK] Each turn starts with 5 mana for each hero. 
[OK] The deck called DishDeck has (mana cost, attack, health): 
- Brown rice: (1,1,2)
- French Fries: (1,2,1)
- Green salad: (2,2,3)
- Tomato salad: (2,3,2)
- Poke bowl: (3,2,4)
- Pumpkin Soup: (4,2,7)
- Noodle soup: (4,5,3)
- Spring rolls: (5,3,7)
- Baked Salmon: (5,8,2)

[OK] Deck has 2 of each(18 in total), and randomly shuffled.
[OK] Such that, the first card in deck, costs 1 mana. Second card costs 2 mana or less. And third card costs 4 mana or less.
[OK] No restrictions on rest of cards.

ZetaStone: (like alphastone, except...)
[OK] Uses gammaVictoryStrategy if roundcount is less than 7
[OK] Uses betaVictoryStrategy otherwise

EpsilonStone: (like AlphaStone, except...)
[OK] Findus has FrenchChef, Peddersen has ItalianChef
[OK] FrenchChef's power "Redwine" picks random opp. minion and removes 2 health
[OK] ItalianChef's power "Pasta" picks random own minion and adds 2 attack
[OK] "Redwine" does nothing if there are no opp. minions.
[OK] "Pasta" does nothing if there are no own minions.

EtaStone: (like DELTASTONE!, except...)
[OK] Players have a new deck: Effect Dish Deck.
[OK] Cards in EDDeck have "card effects".
[OK] Card effects are executed when the card is played,
    and take effect before the card enters the field.

[OK] Brown Rice: (1,1,1): Deal 1 damage to opp. hero;
[OK] Tomato Salad: (2,2,2): Add +1 atk. to rand. minion; 
[OK] Poke Bowl: (3,2,3): Restore +2 health to hero;
[OK] Noodle Soup: (4,5,3): Draw card;
[OK] Spring Rolls: (5,3,5): Destroy rand. opp. minion;
[OK] Baked Salmon: (5,7,6): Add +2 atk. to rand. opp. minion.

SemiStone:
[OK] Implemented as SemiFactory, SemiHeroStrategy
Combination of Strategies from previous variants. No tests (yet).
