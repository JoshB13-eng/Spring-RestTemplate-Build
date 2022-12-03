
//Controller class
package JoshA.Control;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity; 
import org.springframework.web.bind.annotation.PathVariable; 
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody; 
import org.springframework.web.bind.annotation.RequestMapping; 
import org.springframework.web.bind.annotation.RequestMethod; 
import org.springframework.web.bind.annotation.RequestHeader; 
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import java.util.Map;
import java.util.HashMap;
import JoshA.DataBox.*;


@RestController
@RequestMapping(path = "/JoshFruit")
public class FruitController{
    
    private Map<Integer,Fruit> fruit;
    
    public FruitController(){
        this.fruit = new HashMap();
        Fruit fruitObj = new Fruit();
        
        Apple apple = new Apple();
        apple.setNumberOfApple(5);
        apple.setAmount(500);
        
        Lemon lemon = new Lemon();
        lemon.setNumberOfLemon(5);
        lemon.setAmount(250);
        
        Orange orange = new Orange();
        orange.setNumberOfOrange(5);
        orange.setAmount(150);
        
        fruitObj.setNumberOfFruit(5);
        fruitObj.setApple(apple);
        fruitObj.setLemon(lemon);
        fruitObj.setOrange(orange);
        
        this.fruit.put(1,fruitObj);
        
        Fruit fruitObj2 = new Fruit();
        
        Apple apple2 = new Apple();
        apple2.setNumberOfApple(10);
        apple2.setAmount(1000);
        
        Lemon lemon2 = new Lemon();
        lemon2.setNumberOfLemon(10);
        lemon2.setAmount(600);
        
        Orange orange2 = new Orange();
        orange2.setNumberOfOrange(10);
        orange2.setAmount(300);
        
        fruitObj2.setNumberOfFruit(10);
        fruitObj2.setApple(apple2);
        fruitObj2.setLemon(lemon2);
        fruitObj2.setOrange(orange2);
        
        this.fruit.put(2,fruitObj2);
    }
    
    @RequestMapping(path = "/Example")
    public ResponseEntity<Object> ShowExample(@RequestHeader(name = "host", defaultValue = "DefaultHost", required = false)String Host, @RequestHeader(name = "accept-encoding", defaultValue = "DefaultAccept-Encoding", required = true)String AccEnc, @RequestParam("str")String str, @RequestParam("int")Integer ina){
        //Get headers and parameters from request and send it as response to client
        String stra = "\nFrom the RequestHeaders Host:"+Host+", Accept-Encoding:"+AccEnc+"\nWhile From Query parameters we have("+str+") and ("+ina+") as our values\n";
        
        return new ResponseEntity<>(stra,HttpStatus.OK);
    }
    
    @GetMapping(path = "/Fruit", produces = "application/json")
    public ResponseEntity<Object> getFruit(){
        //Get all fruit bean and send it to client
        return new ResponseEntity<>(this.fruit,HttpStatus.OK);
    }
    
    
    @GetMapping(path = "/Fruit/{No1}/{No2}", produces = "application/json")
    public ResponseEntity<Object> getFruitX(@PathVariable("No1") Integer No1, @PathVariable("No2") Integer No2){
        //Get two specific fruit bean with index from request path
        if((!(this.fruit.containsKey(No1)))||(!(this.fruit.containsKey(No2)))){
            //If map header doesnt have FruitIndex gotten from request path
            String str = "\nEither MapKey/FruitIndex "+No1+" or "+No2+" doesnt exist!!.\n Try getting MapKey/FruitIndex 1 and 2 if you have not deleted them.\n";
        
            return new ResponseEntity<>(str,HttpStatus.OK);
        }
        
        
        Map<Integer,Fruit> SpecifiedHeader = new HashMap<>();
        
        SpecifiedHeader.put(No1,this.fruit.get(No1));
        
        SpecifiedHeader.put(No2,this.fruit.get(No2));
        
        return new ResponseEntity<>(SpecifiedHeader,HttpStatus.OK);
        
    }
    
    
    @PostMapping(path = "/Fruit/Create/{FruitIndex}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Object> createFruit(@RequestBody Fruit fruitp, @PathVariable("FruitIndex")Integer FruitIndex){
        //Create a fruit bean element in map
        this.fruit.put(FruitIndex,fruitp);
        
        return new ResponseEntity<>("\nFruit is created successfully\n", HttpStatus.CREATED);
    }
    
    @PutMapping(path = "/Fruit/Update/{FruitIndex}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Object> update(@RequestBody Fruit fruitp/**1**/, @PathVariable("FruitIndex") Integer FruitIndex){
        //Update a fruit bean element in map
        if(this.fruit.containsKey(FruitIndex)){
            
            this.fruit.remove(FruitIndex);
            //If it contains map key, then remove it
        } 
        
        this.fruit.put(FruitIndex,fruitp);
        
        String str = "\nIndex "+FruitIndex+" of Fruit has been sucessfully updated\n";
        
        return new ResponseEntity<>(str, HttpStatus.OK);
    }
    
    @DeleteMapping(path = "/Fruit/Delete/{FruitIndex}")
    public ResponseEntity<Object> delete(@PathVariable("FruitIndex")Integer FruitIndex){
    //Delete a fruit bean element in map
        
        this.fruit.remove(FruitIndex);
        
        String str = "\nIndex "+FruitIndex+" of Fruit has been deleted sucessfully\n";
        
        return new ResponseEntity<>(str, HttpStatus.OK);
    }
    
    @RequestMapping(path = "/Exit")
    public ResponseEntity<Object> Exit(){
        //Exit app
        return new ResponseEntity<>("\nShutting down server...\n", HttpStatus.OK);
    }
}

//Browsers: curl http://localhost:9090/JoshFruit/Example?str=StringFromQueryParams&int=10

//Shell:curl http://localhost:9090/JoshFruit/Example?str=StringFromQueryParams\&int=10
//THE ABOVE IS FOR .ShowExample(..)


//Sending GET request
//Browsers: http://localhost:9090/JoshFruit/Fruit
//Shell: curl http://localhost:9090/JoshFruit/Fruit
//THE ABOVE IS FOR .getFruit()

//Browsers: http://localhost:9090/JoshFruit/Fruit/1/2
//Shell: curl http://localhost:9090/JoshFruit/Fruit/1/2
//THE ABOVE IS FOR .getFruitX()


//Sending DELETE request
//Shell: curl -X "DELETE" http://localhost:9090/JoshFruit/Fruit/Delete/3
//THE ABOVE IS FOR .delete(..)


//Sending POST request
//Shell: curl -H "Content-Type: application/json" -X POST -d '{"numberOfFruit":25,"orange":{"numberOfOrange":25,"amount":2500},"apple":{"numberOfApple":25,"amount":12500},"lemon":{"numberOfLemon":25,"amount":7500}}' http://localhost:9090/JoshFruit/Fruit/Create/6
//THE ABOVE IS FOR .createFruit(..)


//Shell: curl -X PUT -d '{"numberOfFruit":15,"orange":{"numberOfOrange":15,"amount":1500},"apple":{"numberOfApple":15,"amount":7500},"lemon":{"numberOfLemon":15,"amount":4500}}' -H "Content-Type: application/json" http://localhost:9090/JoshFruit/Fruit/Update/6
//THE ABOVE IS FOR .update(..)



//In order to exist this program:
//Shell: curl http://localhost:9090/JoshFruit/Exit