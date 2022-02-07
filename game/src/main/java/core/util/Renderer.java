package core.util;

import util.Util;

public class Renderer {

    private static StringBuilder render = new StringBuilder();

    Renderer() {
        throw new IllegalStateException("Utility class");
    }

    public static void clear() {
        render = new StringBuilder();
    }

    public static void add(String str) {
        render.append(str);
        render.append(System.lineSeparator());
        Util.logger.info(str);
    }

    public static StringBuilder getRender() {
        return render;
    }

    public static void print(int print, int nbGame, int nbGamePlayed) {
        if (print == 1) {
            System.out.println(render.toString());
        }
        System.out.println(System.lineSeparator());
        System.out.println(String.format("%d / %d partie(s) de Seasons ont ete jouees.", nbGamePlayed, nbGame));
        System.out.println(System.lineSeparator());
    }
}
