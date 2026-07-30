package negocio;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public abstract class Duck {
    protected String name;
    protected FlyBehavior flyBehavior;
    protected QuackBehavior quackBehavior;

    public void performFly() {
        this.flyBehavior.fly();
    }

    public void performQuack() {
        this.quackBehavior.quack();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public FlyBehavior getFlyBehavior() {
        return flyBehavior;
    }

    public void setFlyBehavior(FlyBehavior flyBehavior) {
        this.flyBehavior = flyBehavior;
    }

    public QuackBehavior getQuackBehavior() {
        return quackBehavior;
    }

    public void setQuackBehavior(QuackBehavior quackBehavior) {
        this.quackBehavior = quackBehavior;
    }

    public void display() {
        ImageIcon imageIcon = new ImageIcon(
                "/home/iapereira/git/ppp2026_1sem/01_strategy/codigo/simulador_patos/src/main/resources/"
                        + this.getClass().getSimpleName().toLowerCase() + ".png");
        JFrame jFrame = new JFrame();
        jFrame.setSize(500, 500);
        jFrame.getContentPane().setLayout(new BoxLayout(jFrame.getContentPane(), BoxLayout.Y_AXIS));

        JLabel fly = new JLabel(this.flyBehavior.getClass().getSimpleName());
        jFrame.add(fly);

        JLabel jLabel = new JLabel();
        jLabel.setIcon(imageIcon);
        jFrame.add(jLabel);

        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        SoundPlayerFrame.playSound("simulador_patos/src/main/resources/quack.wav");
    }
}
