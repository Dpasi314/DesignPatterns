import Composite.*;
import Prototype.ShapeCache;
import Strategy.*;
import Template.*;
import Singleton.*;
import Adapter.*;
import Factory.*;
import Flyweight.*;
import Decorator.*;
import Iterator.*;
import Observer.*;
import Proxy.*;
import Command.*;
import Memento.*;
import State.*;
import Prototype.*;
import ChainOfResponsibility.*;
import Visitor.*;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DesignPatterns {
     public static void main(String[] args) {
         /**
          * Strategy Design Pattern
          *
          * There are common situations when classes differ only in their behavior.
          * For this cases is a good idea to isolate the algorithms in separate classes
          * in order to have the ability to select different algorithms at runtime.
          */
         Behavior behaviors[] = {new NormalBehvaior(), new ExtremeBehvaior()};
         for(Behavior b : behaviors)
             b.behave();

         /**
          * Template Design Pattern
          *
          * Define the skeleton of an algorithm in an operation, deferring some steps to client subclasses.
          * Template Method lets subclasses redefine certain steps of an algorithm without
          * changing the algorithm's structure.
          */
         CaffeineBeverage coffee = new Coffee();
         CaffeineBeverage tea = new Tea();

         coffee.prepareRecipe();
         tea.prepareRecipe();

         System.out.println("----------");
         /**
          * Singleton Design Pattern
          *
          * The singleton pattern is one of the simplest design patterns:
          * it involves only one class which is responsible to instantiate itself,
          * to make sure it creates not more than one instance; in the same time it
          * provides a global point of access to that instance. In this case the same
          * instance can be used from everywhere, being impossible to invoke directly
          * the constructor each time.
          */

         RandomSingleton r = RandomSingleton.getRandomSingletonInstance(10);
         List<Integer> rands = new ArrayList<Integer>();
         for(int i = 0; i < 10; i++)
             rands.add(r.getRandTo100());
         System.out.println(rands);

         /**
          * Adapter Design Pattern
          *
          * Convert the interface of a class into another interface clients expect.
          * Adapter lets classes work together that couldn't otherwise because of
          * incompatible interfaces.
          */
         System.out.println("----------");

         Target t = new Adapter();
         t.request();

         /**
          * Factory Design Pattern
          *
          * This type of design pattern comes under creational pattern as this pattern
          * provides one of the best ways to create an object. In Factory pattern, we
          * create object without exposing the creation logic to the client and refer to
          * newly created object using a common interface.
          */
         System.out.println("----------");

         AnimalFactory animalFactory = new AnimalFactory();

         Animal giraffe = animalFactory.getAnimal("giraffe");
         Animal fox = animalFactory.getAnimal("fox");

         giraffe.noise();
         fox.noise();

         /**
          * Flyweight Design Pattern
          *
          * Flyweight pattern tries to reuse already existing similar kind objects by
          * storing them and creates new object when no matching object is found
          */
         System.out.println("----------");

         String shades[] = {"Orchid", "Light", "Dark", "Orchid", "Light", "Dark"};
         ColorFactory colorFactory = new ColorFactory();
         for(int i = 0; i < shades.length; i++) {
             Color color = colorFactory.get(shades[i]);
             color.getShade();
         }

         /**
          * Composite Design Pattern
          *
          * Used where we need to treat a group of objects in similar way as a single object.
          * This pattern creates a class that contains group of its own objects. This class
          * provides ways to modify its group of same objects.
          */

         Employee tim = new Employee("Tim");
         Employee aly = new Employee("Aly");
         Employee dante = new Employee("Dante");
         Employee tatyana = new Employee("Tatyana");
         Employee cristian = new Employee("Cristian");
         Employee ahkilesh = new Employee("Akhilesh");

         Employee employees[] = {tim, aly, dante, tatyana, cristian, ahkilesh};

         tim.addSubordinate(aly);
         tim.addSubordinate(dante);
         tim.addSubordinate(tatyana);
         tim.addSubordinate(cristian);
         tim.addSubordinate(ahkilesh);

         for(Employee e : tim.getInChargeOf())
             if(e != aly) aly.addSubordinate(e);

         for(Employee e : aly.getInChargeOf())
             if(e != dante) dante.addSubordinate(e);

         for(Employee e : aly.getInChargeOf())
             if(e != tatyana) tatyana.addSubordinate(e);

         for(Employee e : employees) {
             System.out.println(e.getName() + " --> " + e.getInChargeOf());
         }

         /**
          * Decorator Design Pattern
          *
          * Decorator pattern allows a user to add new functionality to an existing object
          * without altering its structure. This pattern creates a decorator class which
          * wraps the original class and provides additional functionality keeping class
          * methods signature intact.
          */
         System.out.println("----------");

         Decorator.Shape pentagon = new Pentagon();
         pentagon.draw();

         Decorator.Shape purpleOctagon = new PurpleShapeDecorator(new Octagon());
         purpleOctagon.draw();

         Decorator.Shape purplePentagon = new PurpleShapeDecorator(new Pentagon());
         purplePentagon.draw();

         /**
          * Iterator Design Pattern
          *
          * This pattern is used to get a way to access the elements of a collection
          * object in sequential manner without any need to know its underlying representation.
          */
         System.out.println("----------");

         Games games = new Games();

         for(Iterator iter = games.getIterator(); iter.hasNext();){
             Game g = (Game) iter.next();
             System.out.println("Name : " + g.getName());
         }

         /**
          * Observer Design Pattern
          *
          *Observer pattern is used when there is one-to-many relationship between
          * objects such as if one object is modified, its dependent objects are
          * to be notified automatically
          */
         System.out.println("----------");

         Subject subject1 = new Subject("Subject 1");
         Subject subject2 = new Subject("Subject 2");

         new ObserverA(subject1);
         new ObserverB(subject1);
         new ObserverC(subject1);

         new ObserverA(subject2);
         new ObserverC(subject2);

         subject1.setState(1);
         subject2.setState(0);
         subject1.setState(10);

         /**
          * Proxy Design Pattern
          *
          * We create object having original object to interface its functionality to outer world.
          * A class represents functionality of another class. Similar to a court case
          * (Drop Dead Diva)
          */
         System.out.println("----------");

         Person p = new Lawyer("Jane Bingum");
         p.talk();
         p.talk(); //Plaintiff doesn't have to get ready for court again.
         // The lawyer represents the Client!!!

         /**
          * Command Design Pattern
          *
          * A request is wrapped under an object as command and passed to invoker object.
          * Invoker object looks for the appropriate object which can handle this command
          * and passes the command to the corresponding object which executes the command
          */
         System.out.println("----------");
         BuyCommand buyCommand = new BuyCommand(10);
         SellCommand sellCommand = new SellCommand(3);
         CommandExecutor commandExecutor = new CommandExecutor();

         commandExecutor.addCommand(buyCommand);
         commandExecutor.addCommand(sellCommand);

         commandExecutor.execute();

         /**
          * Memento Design Pattern
          *
          * Memento pattern is used to restore state of an object to a previous state.
          */
         System.out.println("----------");

         Originator originator = new Originator();
         Originator o2 = new Originator();
         Caretaker caretaker = new Caretaker();

         originator.setState("My first state");
         originator.setState("My second state");
         caretaker.addMemento(originator.createMemento());
         originator.setState("My third state");
         caretaker.addMemento(originator.createMemento());
         originator.setState("A specific 4th state");
         caretaker.addMemento(originator.createMemento());

         o2.setState("O2s FIRST STATE!");
         caretaker.addMemento(o2.createMemento());

         List<Memento> mementos = caretaker.getAllMementos();
         for(Memento m : mementos)
             System.out.println(m.getState());

         System.out.println(caretaker.getMemento(2).getState());

         System.out.println("State before Restore: " + originator.getState());
         originator.setState(caretaker.getMemento(0).getState());
         System.out.println("State after Restore: " + originator.getState());

         /**
          * State Design Pattern
          *
          * In State pattern a class behavior changes based on its state
          */
         System.out.println("----------");
         Context room = new Context();

         FanSpeedExtreme extreme = new FanSpeedExtreme();
         FanSpeedLow low = new FanSpeedLow();

         extreme.execute(room);
         low.execute(room);

         /**
          * Prototype Design Pattern
          *
          * Creates duplicate object while keeping performance in mind.
          * This pattern involves implementing a prototype interface which
          * tells to create a clone of the current object. This pattern is
          * used when creation of object directly is costly.
          */
         System.out.println("----------");

         ShapeCache.load();

         Prototype.Shape clonedHexagon = ShapeCache.get("1");
         Prototype.Shape clonedRhombus = ShapeCache.get("2");

         System.out.println(clonedHexagon.getType() + "\n" + clonedRhombus.getType());

         /**
          * Chain of Responsibility Design Pattern
          *
          * chain of responsibility pattern creates a chain of receiver objects
          * for a request. This pattern decouples sender and receiver of a request
          * based on type of request.
          */
         System.out.println("----------");
         AbstractLogger log1 = new Logger1();
         AbstractLogger log2 = new Logger2();
         AbstractLogger log3 = new Logger3();

         log1.setNextLogger(log2);
         log2.setNextLogger(log3);
         log1.log("MEOW");

         /**
          * Visitor Design Pattern
          * 
          * We use a visitor class which changes the executing algorithm of an element class.
          * By this way, execution algorithm of element can vary as and when visitor varies.
          */
         System.out.println("----------");
         Item cart = new Cart();
         cart.accept(new ItemVisitorImpl());
     }
}
