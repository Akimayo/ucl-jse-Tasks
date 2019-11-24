package cz.mciesla.ucl;

import cz.mciesla.ucl.logic.Program;
import cz.mciesla.ucl.logic.IAppLogic;
import cz.mciesla.ucl.ui.definition.IUserInterface;
import cz.mciesla.ucl.ui.gui.GUI;
import cz.mciesla.ucl.ui.cli.CLI;
//import cz.mciesla.ucl.ui.gui.GUI; // This is for BONUS task only

public class Main {
    public static void main(String[] args) {
        IAppLogic program = new Program();
        IUserInterface ui;
        if(args.length > 0 && args[0].equals("nogui")) ui = new CLI();
        else ui = new GUI();

        program.generateMockData();

        ui.run(program);
    }
}
