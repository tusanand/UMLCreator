public final class Config {
    private Config() {
    }

    public static int PADDING = 15;
    public static int OFFSET = 20;

    public static int WINDOW_START_X = 10;
    public static int WINDOW_START_Y = 10;
    public static int WINDOW_WIDTH = 1000;

    public static int DESCRIPTOR_WIDTH = 300;
    public static int UML_WIDTH = WINDOW_WIDTH - DESCRIPTOR_WIDTH - OFFSET - 3 * PADDING;

    public static int UML_DESCRIPTOR_HEIGHT = 600;

    public static int LOGGER_HEIGHT = 30;

    public static int BOX_WIDTH = 70;
    public static int BOX_HEIGHT = 40;

    public static int BOX_TEXT_OFFSET = 5;

    public static int BUTTON_HEIGHT = 80;

    public static int BTN_RUN_WIDTH = 180;
    public static int BTN_CLEAR_WIDTH = 100;
    public static int BTN_LOAD_ASYM_WIDTH = 220;
    public static int BTN_LOAD_SYM_WIDTH = 220;

    public static int GRAPH_HEIGHT = 500;

    public static int TOTAL_ENTRIES = 10;

    public static int POINT_DIAMETER = 16;
    public static int HIGHLIGHT_DIAMETER = 4;

    public static int WINDOW_HEIGHT = 7 * PADDING + UML_DESCRIPTOR_HEIGHT + LOGGER_HEIGHT;
}
