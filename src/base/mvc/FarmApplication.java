package base.mvc;

/** Main runner of the program
 *
 */
public class FarmApplication {
    public static void main(String[] args) {
        FarmModel farmModel = new FarmModel();
        FarmView farmView = new FarmView();

        FarmController farmController = new FarmController(farmView, farmModel);
        farmController.launch();
        farmView.setVisible(true);
    }
}