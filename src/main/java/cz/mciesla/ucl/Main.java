package cz.mciesla.ucl;

import cz.mciesla.ucl.logic.Program;
import cz.mciesla.ucl.logic.IAppLogic;
import cz.mciesla.ucl.ui.definition.IUserInterface;
import cz.mciesla.ucl.ui.cli.CLI;
//import cz.mciesla.ucl.ui.gui.GUI; // This is for BONUS task only

public class Main {
    public static void main(String[] args) {
        IAppLogic program = new Program();
        IUserInterface ui = new CLI();

        program.generateMockData();

        ui.run(program);
    }
}
