from abc import ABC, abstractmethod

# Abstract products
class Button(ABC):
    @abstractmethod
    def render(self):
        pass

class Checkbox(ABC):
    @abstractmethod
    def render(self):
        pass

# Concrete products
class WindowsButton(Button):
    def render(self):
        print("Rendering Windows Button")

class WindowsCheckbox(Checkbox):
    def render(self):
        print("Rendering Windows Checkbox")

class MacOSButton(Button):
    def render(self):
        print("Rendering MacOS Button")

class MacOSCheckbox(Checkbox):
    def render(self):
        print("Rendering MacOS Checkbox")

# Abstract factory
class GuiFactory(ABC):
    @abstractmethod
    def create_button(self):
        pass

    @abstractmethod
    def create_checkbox(self):
        pass

# Concrete factories
class WindowsGuiFactory(GuiFactory):
    def create_button(self):
        return WindowsButton()

    def create_checkbox(self):
        return WindowsCheckbox()

class MacOSGuiFactory(GuiFactory):
    def create_button(self):
        return MacOSButton()

    def create_checkbox(self):
        return MacOSCheckbox()

# Example application
class Application:
    def __init__(self, factory: GuiFactory):
        self.button = factory.create_button()
        self.checkbox = factory.create_checkbox()

    def render(self):
        self.button.render()
        self.checkbox.render()

# Main entry point
if __name__ == "__main__":
    os = "Windows"  # Change to "MacOS" for MacOSGuiFactory
    factory = WindowsGuiFactory() if os == "Windows" else MacOSGuiFactory()

    app = Application(factory)
    app.render()
