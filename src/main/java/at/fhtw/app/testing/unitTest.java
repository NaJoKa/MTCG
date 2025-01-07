/*package at.fhtw.app.testing;
import at.fhtw.app.model.Card;
import at.fhtw.app.model.Scoreboard;
import at.fhtw.app.model.User;
import at.fhtw.app.service.battle.BattleService;
import at.fhtw.app.service.session.SessionService;
import at.fhtw.app.service.user.UserService;
import org.junit.Test;

import static org.junit.Assert.*;

public class unitTest {
    @Test
    public void testUserRegistration() {
        UserService userService = new UserService();
        boolean result = userService.register("player1", "password123");
        assertTrue(result, "User registration should be successful");
    }

    @Test
    public void testUserLogin() {
        UserService userService = new UserService();
        userService.register("player1", "password123");
        String token = userService.login("player1", "password123");
        assertNotNull(token, "Login should return a valid token");
    }

    @Test
    public void testCardCreation() {
        Card card = new Card("FireDragon", 100, "fire", CardType.MONSTER);
        assertEquals("FireDragon", card.getName());
        assertEquals(100, card.getDamage());
        assertEquals("fire", card.getElement());
        assertEquals(CardType.MONSTER, card.getType());
    }

    @Test
    public void testAddCardToStack() {
        User user = new User("player1");
        Card card = new Card("FireDragon", 100, "fire", CardType.MONSTER);
        user.addCardToStack(card);
        assertEquals(1, user.getStack().size(), "Stack should have 1 card");
    }

    @Test
    public void testRemoveCardFromStack() {
        User user = new User("player1");
        Card card = new Card("FireDragon", 100, "fire", CardType.MONSTER);
        user.addCardToStack(card);
        user.removeCardFromStack(card);
        assertEquals(0, user.getStack().size(), "Stack should be empty after removal");
    }

    @Test
    public void testAcquirePackage() {
        UserService userService = new UserService();
        User user = new User("player1");
        user.setCoins(20);
        boolean result = userService.acquirePackage(user);
        assertTrue(result, "User should be able to acquire a package");
        assertEquals(15, user.getCoins(), "Coins should be deducted");
    }

    @Test
    public void testDeckDefinition() {
        User user = new User("player1");
        Card card1 = new Card("FireDragon", 100, "fire", CardType.MONSTER);
        Card card2 = new Card("WaterSpell", 50, "water", CardType.SPELL);
        user.addCardToDeck(card1);
        user.addCardToDeck(card2);
        assertEquals(2, user.getDeck().size(), "Deck should have 2 cards");
    }

    @Test
    public void testBattleRound() {
        BattleService battleService = new BattleService();
        Card card1 = new Card("FireDragon", 100, "fire", CardType.MONSTER);
        Card card2 = new Card("WaterSpell", 50, "water", CardType.SPELL);
        Card winner = battleService.determineRoundWinner(card1, card2);
        assertEquals(card2, winner, "WaterSpell should win due to elemental advantage");
    }

    @Test
    public void testBattleDraw() {
        BattleService battleService = new BattleService();
        Card card1 = new Card("FireDragon", 100, "fire", CardType.MONSTER);
        Card card2 = new Card("FireDragon", 100, "fire", CardType.MONSTER);
        Card winner = battleService.determineRoundWinner(card1, card2);
        assertNull(winner, "Battle round should result in a draw");
    }

    @Test
    public void testGoblinFearOfDragons() {
        BattleService battleService = new BattleService();
        Card goblin = new Card("Goblin", 50, "normal", CardType.MONSTER);
        Card dragon = new Card("FireDragon", 100, "fire", CardType.MONSTER);
        Card winner = battleService.determineRoundWinner(goblin, dragon);
        assertEquals(dragon, winner, "Goblins should not attack dragons");
    }

    @Test
    public void testWizardControlsOrks() {
        BattleService battleService = new BattleService();
        Card wizard = new Card("Wizard", 80, "normal", CardType.MONSTER);
        Card ork = new Card("Ork", 90, "normal", CardType.MONSTER);
        Card winner = battleService.determineRoundWinner(wizard, ork);
        assertEquals(wizard, winner, "Wizard should control Orks");
    }

    @Test
    public void testKrakenImmunityToSpells() {
        BattleService battleService = new BattleService();
        Card kraken = new Card("Kraken", 200, "water", CardType.MONSTER);
        Card spell = new Card("FireSpell", 50, "fire", CardType.SPELL);
        Card winner = battleService.determineRoundWinner(kraken, spell);
        assertEquals(kraken, winner, "Kraken should be immune to spells");
    }

    @Test
    public void testFireElvesEvadeDragons() {
        BattleService battleService = new BattleService();
        Card fireElf = new Card("FireElf", 60, "fire", CardType.MONSTER);
        Card dragon = new Card("FireDragon", 100, "fire", CardType.MONSTER);
        Card winner = battleService.determineRoundWinner(fireElf, dragon);
        assertEquals(fireElf, winner, "FireElves should evade Dragons");
    }

    @Test
    public void testScoreboardUpdateAfterWin() {
        Scoreboard scoreboard = new Scoreboard();
        User player = new User("player1");
        scoreboard.update(player, true);
        assertEquals(103, player.getElo(), "ELO should increase by 3 after a win");
    }

    @Test
    public void testScoreboardUpdateAfterLoss() {
        Scoreboard scoreboard = new Scoreboard();
        User player = new User("player1");
        scoreboard.update(player, false);
        assertEquals(95, player.getElo(), "ELO should decrease by 5 after a loss");
    }

    @Test
    public void testTradingSetup() {
        TradingService tradingService = new TradingService();
        User player = new User("player1");
        Card card = new Card("WaterGoblin", 50, "water", CardType.MONSTER);
        player.addCardToStack(card);
        boolean result = tradingService.createTrade(player, card, "spell", 70);
        assertTrue(result, "Trading setup should be successful");
    }

    @Test
    public void testTradeExecution() {
        TradingService tradingService = new TradingService();
        User playerA = new User("playerA");
        User playerB = new User("playerB");
        Card cardA = new Card("WaterGoblin", 50, "water", CardType.MONSTER);
        Card cardB = new Card("FireSpell", 80, "fire", CardType.SPELL);
        playerA.addCardToStack(cardA);
        tradingService.createTrade(playerA, cardA, "spell", 70);
        boolean result = tradingService.executeTrade(playerB, cardB, cardA);
        assertTrue(result, "Trade should be successful");
    }

    @Test
    public void testProfileUpdate() {
        UserService userService = new UserService();
        User user = new User("player1");
        userService.updateProfile(user, "New bio");
        assertEquals("New bio", user.getBio(), "Profile bio should be updated");
    }

    @Test
    public void testTokenValidation() {
        SessionService SessionService = new SessionService();
        String token = SessionService.generateToken("player1");
        boolean isValid = SessionService.validateToken(token);
        assertTrue(isValid, "Token should be valid");
    }

    @Test
    public void testInvalidToken() {
        SessionService authService = new SessionService();
        boolean isValid = authService.validateToken("invalidToken");
        assertFalse(isValid, "Token should be invalid");
    }
}*/
