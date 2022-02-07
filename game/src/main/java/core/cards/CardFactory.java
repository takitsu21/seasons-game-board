package core.cards;


import core.cards.effects.*;
import core.exception.CardException;
import core.util.CardUtil;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import util.Util;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public class CardFactory {
    private static final CardFactory INSTANCE = new CardFactory();
    private final ArrayList<Card> cards = new ArrayList<>();
    private final JSONParser parser = new JSONParser();

    private CardFactory() {
    }

    public static CardFactory getINSTANCE() {
        return INSTANCE;

    }

    public Class<?> getCard(int id) {
        return switch (id) {
            case 1 -> WindAmuletEffect.class;
            case 2 -> FireAmuletEffect.class;
            case 3 -> EarthAmuletEffect.class;
            case 4 -> WaterAmuletEffect.class;
            case 5 -> BalanceIshtarEffect.class;
            case 6 -> BatonDuPrintempsEffect.class;
            case 7 -> BottesTemporelles.class;
            case 8 -> BourseIoEffect.class;
            case 9 -> CaliceDivinEffect.class;
            case 10 -> SyllasLeFideleEffect.class;
            case 11 -> FigrimAvaricieuxEffect.class;
            case 12 -> NariaLaProphetesseEffect.class;
            case 13 -> CoffretMerveilleuxEffect.class;
            case 14 -> CorneDuMendiant.class;
            case 15 -> DeDeLaMaliceEffect.class;
            case 16 -> KairnDestructeurEffect.class;
            case 17 -> AmsugLongcoupEffect.class;
            case 18 -> GrimoireEnsorceleEffect.class;
            case 19 -> HeaumeRagfieldEffect.class;
            case 20 -> MainFortuneEffect.class;
            case 21 -> LewisGrisemineEffect.class;
            case 22 -> CubeRuniqueEolisEffect.class;
            case 23 -> PotionPuissanceEffect.class;
            case 24 -> PotionRevesEffect.class;
            case 25 -> PotionSavoirEffect.class;
            case 26 -> PotionVieEffect.class;
            case 27 -> SablierTempsEffect.class;
            case 28 -> SceptreGrandeurEffect.class;
            case 29 -> StatueBenieOlafEffect.class;
            case 30 -> VaseOublieYjangEffect.class;
            default -> throw (new CardException("Card not implemented"));
        };
    }

    public Card createCard(Class<?> cl, Object... obj) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Class<?>[] types = new Class[]{
                Integer.class,
                Boolean.class,
                Integer.class,
                List.class,
                EffectFrequency.class,
                String.class,
                Integer.class
        };

        Constructor<?> cons = cl.getConstructor(
                types
        );

        return (Card) cons.newInstance(
                obj
        );
    }

    public void load() {
        try {
            cards.clear();
            URL resourceUrl = getClass().getClassLoader().getResource("cards.json");
            File f = new File(resourceUrl.toURI());
            Object jsonObj = parser.parse(new FileReader(f));
            JSONArray json = (JSONArray) ((JSONObject) jsonObj).get("data");

            for (int i = 0; i < json.size(); i++) {
                JSONObject data = (JSONObject) json.get(i);
                JSONObject cost = (JSONObject) data.get("cost");

                Class<?> cl = getCard(((Long) data.get("id")).intValue());
                Object[] obj = {
                        i + 1,
                        cost.get("players"),
                        ((Long) cost.get("crystal")).intValue(),
                        CardUtil.convertJsonArray((JSONArray) cost.get("energy")),
                        CardUtil.strFrequencyToEffectFrequency((String) data.get("frequence")),
                        data.get("name"),
                        ((Long) data.get("points")).intValue()
                };
                for (int j = 0; j < 2; j++) {
                    cards.add(createCard(cl, obj));
                }
            }
        } catch (IOException | ParseException | ReflectiveOperationException | URISyntaxException e) {
            Util.logger.log(Level.SEVERE, "context", e);
        }
    }

    public List<Card> getCards() {
        return cards;
    }
}
