package hotstone.standard;

import hotstone.framework.*;
import hotstone.framework.Strategies.PrinterStrategy;
import hotstone.standard.Strategies.PrinterStrategy.CommandLinePrinterStrategy;
import hotstone.standard.Strategies.PrinterStrategy.NullPrinterStrategy;

import java.util.List;

public class TranscriptorHotStoneGame implements UIGame {

    private final StandardHotStoneGame game;
    private PrinterStrategy printer;

    public TranscriptorHotStoneGame(VariantFactory variant) {
        game = new StandardHotStoneGame(variant);
        printer = new CommandLinePrinterStrategy();
    }

    public void transcribingOn() {
        printer = new CommandLinePrinterStrategy();
    }

    public void transcribingOff() {
        printer = new NullPrinterStrategy();
    }

    @Override
    public void endTurn() {
        Player p = game.getPlayerInTurn();
        game.endTurn();
        printer.printf(
                "%s ended turn.\n",
                p
        );
    }

    @Override
    public Status playCard(int handIndex, int fieldIndex) {
        Player p = game.getPlayerInTurn();
        Card c = game.getCardInHand(p, handIndex);

        Status s = game.playCard(handIndex, fieldIndex);

        if (s == Status.OK) {
            printer.printf(
                    "%s played %s at index %d.\n",
                    p,
                    c.getName(),
                    fieldIndex
            );
        }
        return s;
    }

    @Override
    public Status attackCard(int ownFieldIndex, int oppFieldIndex) {
        Player p = game.getPlayerInTurn();
        Card attacker = game.getCardInField(p, ownFieldIndex);
        Card defender = game.getCardInField(Player.computeOpponent(p), oppFieldIndex);

        Status s = game.attackCard(ownFieldIndex, oppFieldIndex);
        if (s == Status.OK) {
            printer.printf(
                    "%s attacked %s with %s.\n",
                    p, defender.getName(), attacker.getName()
            );
        }
        return s;
    }

    @Override
    public Status attackHero(int fieldIndex) {
        Player p = game.getPlayerInTurn();
        Card attacker = game.getCardInField(p, fieldIndex);

        Status s = game.attackHero(fieldIndex);
        if (s == Status.OK) {
            printer.printf(
                    "%s attacked Hero with %s.\n",
                    p,
                    attacker.getName()
            );
        }
        return s;
    }

    @Override
    public Status usePower() {
        Status s = game.usePower();
        if (s == Status.OK) {
            printer.printf(
                    "%s used Hero power.\n",
                    game.getPlayerInTurn()
            );
        }
        return s;
    }

    @Override
    public void addObserver(GameObserver observer) {
        game.addObserver(observer);
    }

    @Override
    public int getRoundCount() {
        return game.getRoundCount();
    }

    @Override
    public Player getPlayerInTurn() {
        return game.getPlayerInTurn();
    }

    @Override
    public SecureHero getHero(Player who) {
        return game.getHero(who);
    }

    @Override
    public Player getWinner() {
        return game.getWinner();
    }

    @Override
    public int getTurnNumber() {
        return game.getTurnNumber();
    }

    @Override
    public int getDeckSize(Player who) {
        return game.getDeckSize(who);
    }

    @Override
    public Card getCardInDeck(Player who, int indexInDeck) {
        return game.getCardInDeck(who, indexInDeck);
    }

    @Override
    public Card getCardInHand(Player who, int indexInHand) {
        return game.getCardInHand(who, indexInHand);
    }

    @Override
    public Iterable<? extends Card> getHand(Player who) {
        return game.getHand(who);
    }

    @Override
    public int getHandSize(Player who) {
        return game.getHandSize(who);
    }

    @Override
    public Card getCardInField(Player who, int indexInField) {
        return game.getCardInField(who, indexInField);
    }

    @Override
    public List<? extends Card> getField(Player who) {
        return game.getField(who);
    }

    @Override
    public int getFieldSize(Player who) {
        return game.getFieldSize(who);
    }
}
