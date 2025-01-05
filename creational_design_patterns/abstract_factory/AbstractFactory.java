package abstract_factory;

// Abstract products
interface Button {
    void render();
}

interface Checkbox {
    void render();
}

// Concrete products
class WindowsButton implements Button {
    @Override
    public void render() {
        System.out.println("Rendering Windows Button");
    }
}

class WindowsCheckbox implements Checkbox {
    @Override
    public void render() {
        System.out.println("Rendering Windows Checkbox");
    }
}

class MacOSButton implements Button {
    @Override
    public void render() {
        System.out.println("Rendering MacOS Button");
    }
}

class MacOSCheckbox implements Checkbox {
    @Override
    public void render() {
        System.out.println("Rendering MacOS Checkbox");
    }
}

// Abstract factory
abstract class GuiFactory {
    abstract Button createButton();

    abstract Checkbox creatCheckbox();
}

// Concrete factories
class WindowsGuiFactory extends GuiFactory {
    @Override
    Button createButton() {
        return new WindowsButton();
    }

    @Override
    Checkbox creatCheckbox() {
        return new WindowsCheckbox();
    }
}

class MacOSGuiFactory extends GuiFactory {
    @Override
    Button createButton() {
        return new MacOSButton();
    }

    @Override
    Checkbox creatCheckbox() {
        return new MacOSCheckbox();
    }
}

// Example application
class Application {
    private Button button;
    private Checkbox checkbox;

    public Application(GuiFactory factory) {
        button = factory.createButton();
        checkbox = factory.creatCheckbox();
    }

    public void render() {
        button.render();
        checkbox.render();
    }
}

public class AbstractFactory {
    public static void main(String[] args) {
        GuiFactory factory;

        String os = "Windows";
        if ("Windows".equals(os)) {
            factory = new WindowsGuiFactory();
        } else {
            factory = new MacOSGuiFactory();
        }

        Application app = new Application(factory);
        app.render();
    }
}
