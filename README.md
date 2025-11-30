<h1 align="center">
  /ᐠ > ˕ <マ MeowsterQuest ₊˚⊹♡
</h1>

<p align="center">
  <img src="resources/ThisMeow.gif" alt="MeowsterQuest Header" width="1000" height="250">
</p>



# **Description** 📃
#### MeowsterQuest is a turn-based RPG loop of exploration, combat, and rewards to keep gameplay engaging. It solves the problem of repetitive RPGs by combining strategic battles with a clear progression, letting players rescue captured citizens and restore peace to Pawshire.

#### It demonstrates the practical use of Object-oriented Programming (OOP) concepts such [Babaguhin]as encapsulation, inheritance, polymorphism, and abstraction, alongside proper file handling and modular design.


### WHAT CAN YOU DO?
### Player can:
- Choose Cat Breed
- Customized it by color
- Pick special moves
- Gain Hp and Points
- Win the Battle(eme)

# **Game Structure** 
```
📂 src/
└── 📂 combat/
    ├── ⚔️ Attack.java          
    ├── 🛡️ BattleSystem.java
└── 📂 core/
     └── 👾 Game.java
     └── 📍 Main.java
└── 📂 entity/
    └── 📂 player/
        └── 👿 Enemy.java
        └── 🔊 NPC.java
└── 📂 system/
     └── 🐱 Characters.java
     └── 📋 Menu.java
     └── 🏰 Tower.java
└── 📂 util/
     └── 🎨 ColorUtil.java
     └── 💬TextUtil.java
```
- **Attack.java** – Defines attack actions with damage and energy cost attributes.
- **BattleSystem.java** – Manages turn-based combat between player and enemy, including special abilities and battle flow.
- **Game.java** – Oversees game progression, including menu navigation, character creation, and victory sequence.
- **Main.java** – Entry point of the program, initializes the game and handles user input.
- **Enemy.java** – Represents enemy characters with stats, ASCII art, and combat behavior.
- **NPC.java** – Models non-playable characters with names, roles, and dialogue interactions.
- **Character.java** – Handles character creation, breed and color selection, and NPC encounter scenes.
- **Menu.java** – Displays game menus, introduction, victory, and end screens with interactive options.
- **Tower.java** – Controls level progression, enemy battles, prisoner rescues, and retry logic.
- **ColorUtil.java** – Provides color formatting utilities for text output based on cat color traits.
- **TextUtil.java** – Offers text display utilities like typewriter effects, centered printing, and screen clearing.

### How to Run the Game

**1. Navigate to the project root**
- #### Open your terminal and go to the root directory of your project (where the `src` folder or packages like `core`, `combat`, etc. are located).
```
cd path/to/MEOWSTERQUEST
```
** 2. Compile the Java files **
#### Assuming your source files are inside a `src` folder and follow a package structure (`core`, `combat`, etc.), compile all `.java` files:

```
javac src/**/*.java
```
** 3. Run the main class ** 
#### Your entry point is `core.Main`, so run it using:
```
java core.Main
```
#### Make sure you're in the same directory where the compiled `.class` files are located, or set the classpath explicitly:
```
java -cp src core.Main
```

### **What can YOU do (Features)**
1. **Add info.** Enter the name of your character
#### `Enter your cat's name:`
2. **Select Character.** Can choose the breed and color of the cat by selecting the designated number.

#### `=== Choose Your Cat Breed (Class) ===`

<p float="left">
  <img src="resources/Persian.png" width="200" height="200" style="margin-right: 10px;"/>
  <img src="resources/Ragdoll.png" width="200" height="200" style="margin-right: 10px;"/>
  <img src="resources/Puskal.png" width="200" height="200" style="margin-right: 10px;"/>

#### `=== Choose Your Cat Color (Elemental Trait) ===`
</p>
<p align="center">
  <img src="resources/CatColorSample.png" alt="naur it should hav meowmeow" width="400" height="250">
</p>

3. **Battle Actions.** Player can Attack, Defend, Trigger Special Actions, Regenerate Energy, and Level Up.

4. **Tower Progression.** Fights through a three(3) level tower, Rescue prisoners, Retry battles if defeated, and Interacts with NPC.

5. **Immersion.** Enjoy the ASCII art graphics and the charactersirics of each Heroes through the **Colors**, and Experience **Typewriter-style dialouge.**


# **Object-Oriented Principles**

## Abstraction
#### Abstraction is applied through the use of helper classes that hide low-level `details.TextUtil.java` handles spacing, delays, centering, and dynamic printing, while `ColorUtil.java` manages ANSI color formatting. These classes allow the game logic to remain clean and easy to read, since higher-level methods like `printCentered()` or `orange()` hide the underlying implementation. The `NPC` class also provides abstraction through its `speak()` method, which manages the formatting and animation of dialogue internally.

## Encapsulation
#### Encapsulation is used throughout the project by keeping fields private and exposing only controlled methods. Examples include the `NPC` class where the name and role fields are private and accessed only through getter methods. This prevents unwanted modification of game-critical data. Utility classes such as `ColorUtil.java` and `TextUtil.java` also encapsulate complex operations like applying ANSI colors, centering text, and creating typewriter effects. The rest of the project does not need to know the internal logic and can simply call the provided methods.

## Polymorphism
#### Polymorphism is present in interactions with entities. `Player` and `Enemy` objects share the same base type, allowing the program to call common methods while still reacting differently depending on which object is being used. Attack objects are another form of polymorphism. They follow the same structure but have different stats and effects, enabling the combat system to work with them in a flexible and dynamic way.

## Inheritance
#### Inheritance is used in the overall structure of the combat and entity system. Classes such as Player and Enemy extend a common base entity class and share attributes such as health points, attack lists, and names. This allows shared behavior while still supporting unique features for different entity types. This setup also makes the project ready for expansion, since new types of enemies or special player subclasses can easily inherit from the base class.

# Game Play (Example Output)
### snippet
### snippet
### snippet


# Development team (MEO\V3X)


  
| Pic | Name   |Roles    | Account |
|-----|--------|---------|---------|
| <div align="center"><img src="resources/Xiamara.jpg" width="150"></div> | Bernardo, Xiamara| Singer|  link    |
| <div align="center"><img src="resources/miky.jpg" width="150"></div> |Carranceja, Mikyla | Drumer | link    |
| <div align="center"><img src="resources/shanlee.jpg" width="150"></div> |Gupilan, Shanlee Yvonne | Dancer| link    |
| <div align="center"><img src="resources/aaron.jpg" width="150"></div> | Mercado, Aaron Daniel |Posa  | link    |





