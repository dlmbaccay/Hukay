package base.mvc;

import javax.swing.*;
import java.awt.*;

import java.util.ArrayList;
import java.util.HashMap;

/** Manages the visuals of the game
 *
 */
public class FarmView extends JFrame {

    FarmView() {
        ImageIcon hukayLogo = new ImageIcon("resources/hukay-logo.png");
        this.setIconImage(hukayLogo.getImage());
    }

    // start phase variables
    private final JButton START_BUTTON = new JButton();
    private final JPanel START_PANEL = new JPanel() {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            ImageIcon startPage = new ImageIcon("resources/start_phase/hukay-start-page.png");
            g.drawImage(startPage.getImage(), 0, 0, null);
        }
    };

    /**
     * initializes the JFrame to the appropriate size needed for the startPhase
     * Start Panel houses the start button.
     */
    protected void startPhase() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(510, 288);
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        START_PANEL.setLayout(null);

        START_BUTTON.setBounds(187, 155, 125, 50);
        START_BUTTON.setFocusable(true);
        START_BUTTON.setContentAreaFilled(false);

        START_PANEL.add(START_BUTTON);
        this.add(START_PANEL);
    }

    // game phase
    private final JPanel GAME_PANEL = new JPanel();
    private final JPanel BOARD_PANEL = new JPanel();
    private final JPanel LIVE_PANEL = new JPanel() {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(new ImageIcon("resources/game_phase/panel_backgrounds/livePanelBG.png").getImage(), 0, 0, null);
        }
    };
    private final JPanel ACTION_PANEL = new JPanel() { // consists of toolsPanel and cropsPanel
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(new ImageIcon("resources/game_phase/panel_backgrounds/actionPanelBG.png").getImage(), 0, 0, null);
        }
    };

    // live panel variables
    private final JLabel LEVEL_LABEL = new JLabel();
    private final JLabel EXPERIENCE_LABEL = new JLabel();
    private final JLabel STATUS_LABEL = new JLabel();
    private final JLabel OBJECT_COINS_LABEL = new JLabel();
    private final JLabel DETAIL_LABEL = new JLabel();
    private final JLabel DAY_LABEL = new JLabel();

    // board panel variables
    private final HashMap<Integer, JButton> FARM_TILES = new HashMap<>();

    // action button variables
    private final ArrayList<JButton> ACTION_BUTTONS = new ArrayList<>();

    private final JButton HARVEST_BUTTON = new JButton(), REGISTER_BUTTON = new JButton(),
            END_DAY_BUTTON = new JButton(), QUIT_BUTTON = new JButton();

    // tools button variables
    private final ArrayList<JButton> TOOL_BUTTONS = new ArrayList<>();

    private final JButton PLOW_BUTTON = new JButton(), WATER_BUTTON = new JButton(),
            FERTILIZE_BUTTON = new JButton(), PICKAXE_BUTTON = new JButton(),
            SHOVEL_BUTTON = new JButton();

    // crops button variables
    private final ArrayList<JButton> SEED_BUTTONS = new ArrayList<>();

    private final JButton TURNIP_BUTTON = new JButton(), CARROT_BUTTON = new JButton(),
            POTATO_BUTTON = new JButton(), ROSE_BUTTON = new JButton(),
            TULIPS_BUTTON = new JButton(), SUNFLOWER_BUTTON = new JButton(),
            MANGO_BUTTON = new JButton(), APPLE_BUTTON = new JButton();


    /**
     * initializes the JFrame to the appropriate size needed for the gamePhase
     * Consisted of three sub-panels in one main panel.
     * Action Panel is where all the action buttons are located.
     * Board Panel is where all the farm tile buttons are located.
     * Live Panel is where all the farm details are located.
     */
    protected void gamePhase() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1290, 900);
        this.setResizable(false);
        this.setLocationRelativeTo(null);

        GAME_PANEL.setLayout(null);
        GAME_PANEL.setBackground(BROWN);

        LIVE_PANEL.setLayout(null);
        LIVE_PANEL.setBounds(0, 0, 420, 900);

        BOARD_PANEL.setBackground(BROWN);
        BOARD_PANEL.setLayout(new GridLayout(10, 5, 5, 5));
        BOARD_PANEL.setBounds(430, 5, 420, 850);

        ACTION_PANEL.setBounds(860, 0, 420, 900);
        ACTION_PANEL.setBackground(GREEN);
        ACTION_PANEL.setLayout(null);

        setLivePanel();
        setBoardPanel();
        setActionPanel();

        GAME_PANEL.add(LIVE_PANEL);
        GAME_PANEL.add(BOARD_PANEL);
        GAME_PANEL.add(ACTION_PANEL);
        this.add(GAME_PANEL);
    }

    /**
     * Sets the appropriate bounds and sizes for all the components in Live Panel
     */
    private void setLivePanel() {
        Font OpenSymbol = new Font("OpenSymbol", Font.BOLD | Font.ITALIC, 24);

        LEVEL_LABEL.setFont(OpenSymbol);
        LEVEL_LABEL.setForeground(BEIGE);
        LEVEL_LABEL.setBounds(156, 50, 200, 33);

        EXPERIENCE_LABEL.setFont(OpenSymbol);
        EXPERIENCE_LABEL.setForeground(BEIGE);
        EXPERIENCE_LABEL.setBounds(257, 90, 200, 33);

        STATUS_LABEL.setFont(OpenSymbol);
        STATUS_LABEL.setForeground(BEIGE);
        STATUS_LABEL.setBounds(193, 132, 250, 33);

        OBJECT_COINS_LABEL.setFont(OpenSymbol);
        OBJECT_COINS_LABEL.setForeground(BEIGE);
        OBJECT_COINS_LABEL.setBounds(193, 175, 200, 33);

        DAY_LABEL.setBounds(147, 302, 75, 40);
        DAY_LABEL.setForeground(BEIGE);
        DAY_LABEL.setFont(new Font("OpenSymbol", Font.BOLD | Font.ITALIC, 35));

        DETAIL_LABEL.setBounds(99, 507, 221, 260);
        DETAIL_LABEL.setFont(new Font("OpenSymbol", Font.BOLD, 14));
        DETAIL_LABEL.setVerticalAlignment(JLabel.TOP);
        DETAIL_LABEL.setHorizontalAlignment(JLabel.LEFT);
        DETAIL_LABEL.setForeground(BEIGE);

        LIVE_PANEL.add(LEVEL_LABEL);
        LIVE_PANEL.add(EXPERIENCE_LABEL);
        LIVE_PANEL.add(STATUS_LABEL);
        LIVE_PANEL.add(OBJECT_COINS_LABEL);
        LIVE_PANEL.add(DAY_LABEL);
        LIVE_PANEL.add(DETAIL_LABEL);
    }

    /**
     * Sets the appropriate bounds and sizes for all the components in Board Panel
     */
    private void setBoardPanel() {
        for (int position = 1; position <= 50; position++) {
            JButton farmTile = new JButton();

            farmTile.setEnabled(false);
            farmTile.setContentAreaFilled(false);
            farmTile.setBorderPainted(false);

            FARM_TILES.put(position, farmTile);
            BOARD_PANEL.add(FARM_TILES.get(position));
        }
    }

    /**
     * Sets the appropriate bounds and sizes for all the components in Action Panel
     */
    private void setActionPanel() {

        // tool buttons
        PLOW_BUTTON.setBounds(245, 259, 50, 54);
        PLOW_BUTTON.setContentAreaFilled(false);
        WATER_BUTTON.setBounds(214, 201, 50, 54);
        WATER_BUTTON.setContentAreaFilled(false);
        FERTILIZE_BUTTON.setBounds(275, 201, 50, 54);
        FERTILIZE_BUTTON.setContentAreaFilled(false);
        PICKAXE_BUTTON.setBounds(214, 317, 50, 54);
        PICKAXE_BUTTON.setContentAreaFilled(false);
        SHOVEL_BUTTON.setBounds(275, 317, 50, 54);
        SHOVEL_BUTTON.setContentAreaFilled(false);

        ACTION_PANEL.add(PLOW_BUTTON);
        ACTION_PANEL.add(WATER_BUTTON);
        ACTION_PANEL.add(FERTILIZE_BUTTON);
        ACTION_PANEL.add(PICKAXE_BUTTON);
        ACTION_PANEL.add(SHOVEL_BUTTON);

        TOOL_BUTTONS.add(PLOW_BUTTON);
        TOOL_BUTTONS.add(WATER_BUTTON);
        TOOL_BUTTONS.add(FERTILIZE_BUTTON);
        TOOL_BUTTONS.add(PICKAXE_BUTTON);
        TOOL_BUTTONS.add(SHOVEL_BUTTON);

        // action buttons
        HARVEST_BUTTON.setBounds(122, 482, 170, 97);
        HARVEST_BUTTON.setContentAreaFilled(false);
        REGISTER_BUTTON.setBounds(122, 589, 170, 97);
        REGISTER_BUTTON.setContentAreaFilled(false);
        END_DAY_BUTTON.setBounds(72, 696, 125, 97);
        END_DAY_BUTTON.setContentAreaFilled(false);
        QUIT_BUTTON.setBounds(215, 696, 125, 97);
        QUIT_BUTTON.setContentAreaFilled(false);

        ACTION_PANEL.add(HARVEST_BUTTON);
        ACTION_PANEL.add(REGISTER_BUTTON);
        ACTION_PANEL.add(END_DAY_BUTTON);
        ACTION_PANEL.add(QUIT_BUTTON);

        ACTION_BUTTONS.add(HARVEST_BUTTON);
        ACTION_BUTTONS.add(REGISTER_BUTTON);
        ACTION_BUTTONS.add(END_DAY_BUTTON);
        ACTION_BUTTONS.add(QUIT_BUTTON);

        // seed buttons
        TURNIP_BUTTON.setBounds(89, 201, 40, 54);
        TURNIP_BUTTON.setContentAreaFilled(false);
        CARROT_BUTTON.setBounds(89, 259, 40, 54);
        CARROT_BUTTON.setContentAreaFilled(false);
        POTATO_BUTTON.setBounds(89, 317, 40, 54);
        POTATO_BUTTON.setContentAreaFilled(false);
        ROSE_BUTTON.setBounds(140, 201, 40, 54);
        ROSE_BUTTON.setContentAreaFilled(false);
        TULIPS_BUTTON.setBounds(140, 259, 40, 54);
        TULIPS_BUTTON.setContentAreaFilled(false);
        SUNFLOWER_BUTTON.setBounds(140, 317, 40, 54);
        SUNFLOWER_BUTTON.setContentAreaFilled(false);
        MANGO_BUTTON.setBounds(89, 375, 40, 54);
        MANGO_BUTTON.setContentAreaFilled(false);
        APPLE_BUTTON.setBounds(140, 375, 40, 54);
        APPLE_BUTTON.setContentAreaFilled(false);

        ACTION_PANEL.add(TURNIP_BUTTON);
        ACTION_PANEL.add(CARROT_BUTTON);
        ACTION_PANEL.add(POTATO_BUTTON);
        ACTION_PANEL.add(ROSE_BUTTON);
        ACTION_PANEL.add(TULIPS_BUTTON);
        ACTION_PANEL.add(SUNFLOWER_BUTTON);
        ACTION_PANEL.add(MANGO_BUTTON);
        ACTION_PANEL.add(APPLE_BUTTON);

        SEED_BUTTONS.add(TURNIP_BUTTON);
        SEED_BUTTONS.add(CARROT_BUTTON);
        SEED_BUTTONS.add(POTATO_BUTTON);
        SEED_BUTTONS.add(ROSE_BUTTON);
        SEED_BUTTONS.add(TULIPS_BUTTON);
        SEED_BUTTONS.add(SUNFLOWER_BUTTON);
        SEED_BUTTONS.add(MANGO_BUTTON);
        SEED_BUTTONS.add(APPLE_BUTTON);
    }

    // game over phase
    private final JPanel GAME_OVER_PANEL = new JPanel() {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            ImageIcon startPage = new ImageIcon("resources/game_phase/gameOverPanelBG.png");
            g.drawImage(startPage.getImage(), 0, 0, null);
        }
    };
    private final JButton START_OVER_BUTTON = new JButton();
    private final JButton EXIT_GAME_BUTTON = new JButton();

    /**
     * initializes the JFrame to the appropriate size needed for the gameOverPhase
     * Game Over Panel houses the start over and exit game buttons
     */
    protected void gameOverPhase() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(510, 288);
        this.setLocationRelativeTo(null);
        this.setResizable(false);

        GAME_OVER_PANEL.setLayout(null);

        START_OVER_BUTTON.setBounds(108, 81, 125, 97);
        START_OVER_BUTTON.setFocusable(true);
        START_OVER_BUTTON.setContentAreaFilled(false);

        EXIT_GAME_BUTTON.setBounds(266, 81, 125, 97);
        EXIT_GAME_BUTTON.setFocusable(true);
        EXIT_GAME_BUTTON.setContentAreaFilled(false);

        GAME_OVER_PANEL.add(START_OVER_BUTTON);
        GAME_OVER_PANEL.add(EXIT_GAME_BUTTON);
        this.add(GAME_OVER_PANEL);
    }

    // start phase variables getters
    protected JPanel getSTART_PANEL() { return START_PANEL; }
    protected JButton getSTART_BUTTON() { return START_BUTTON; }

    // game phase variables getters
    protected JPanel getGAME_PANEL() { return GAME_PANEL; }

    // live panel button getters
    protected JLabel getLEVEL_LABEL() { return LEVEL_LABEL; }
    protected JLabel getEXPERIENCE_LABEL() { return EXPERIENCE_LABEL; }
    protected JLabel getSTATUS_LABEL() { return STATUS_LABEL; }
    protected JLabel getOBJECT_COINS_LABEL() { return OBJECT_COINS_LABEL; }
    protected JLabel getDAY_LABEL() { return DAY_LABEL; }
    protected JLabel getDETAIL_LABEL() { return DETAIL_LABEL; }

    // farm tiles getter
    protected HashMap<Integer, JButton> getFARM_TILES() { return FARM_TILES; }

    // action button getters
    protected ArrayList<JButton> getACTION_BUTTONS() { return ACTION_BUTTONS; }
    protected JButton getHARVEST_BUTTON() { return HARVEST_BUTTON; }
    protected JButton getREGISTER_BUTTON() { return REGISTER_BUTTON; }
    protected JButton getEND_DAY_BUTTON() { return END_DAY_BUTTON; }
    protected JButton getQUIT_BUTTON() { return QUIT_BUTTON; }

    // tool button getters
    protected ArrayList<JButton> getTOOL_BUTTONS() { return TOOL_BUTTONS; }
    protected JButton getPLOW_BUTTON() { return PLOW_BUTTON; }
    protected JButton getWATER_BUTTON() { return WATER_BUTTON; }
    protected JButton getFERTILIZE_BUTTON() { return FERTILIZE_BUTTON; }
    protected JButton getPICKAXE_BUTTON() { return PICKAXE_BUTTON; }
    protected JButton getSHOVEL_BUTTON() { return SHOVEL_BUTTON; }

    // seed button getters
    protected ArrayList<JButton> getSEED_BUTTONS() { return SEED_BUTTONS; }
    protected JButton getTURNIP_BUTTON() { return TURNIP_BUTTON; }
    protected JButton getCARROT_BUTTON() { return CARROT_BUTTON; }
    protected JButton getPOTATO_BUTTON() { return POTATO_BUTTON; }
    protected JButton getROSE_BUTTON() { return ROSE_BUTTON; }
    protected JButton getTULIPS_BUTTON() { return TULIPS_BUTTON; }
    protected JButton getSUNFLOWER_BUTTON() { return SUNFLOWER_BUTTON; }
    protected JButton getMANGO_BUTTON() { return MANGO_BUTTON; }
    protected JButton getAPPLE_BUTTON() { return APPLE_BUTTON; }

    // game over phase variables getters
    protected JPanel getGAME_OVER_PANEL() { return GAME_OVER_PANEL; }
    protected JButton getSTART_OVER_BUTTON() { return START_OVER_BUTTON; }
    protected JButton getEXIT_GAME_BUTTON() { return EXIT_GAME_BUTTON; }

    // colorway initialization
    private final Color BROWN = new Color(0x996B49);
    private final Color GREEN = new Color(0x718F3F);
    private final Color BEIGE = new Color(0xE4BD98);
}