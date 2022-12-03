
package JoshA.Control;

import java.util.Arrays; 
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType; 
import org.springframework.web.bind.annotation.PathVariable; 
import org.springframework.web.bind.annotation.RequestBody; 
import org.springframework.web.bind.annotation.RequestMapping; 
import org.springframework.web.bind.annotation.RequestMethod; 
import org.springframework.web.bind.annotation.RestController; 
import org.springframework.web.client.RestTemplate; 
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import JoshA.DataBox.*;
 

@RequestMapping(path = "/ConsumeWeb")  
@RestController 
public class ConsumeWebService{
    
    public RestTemplate restTemplate; 
    
    public  ConsumeWebService(){
        
        restTemplate = new RestTemplate();
    }
    
    @RequestMapping(path = "/Example") 
    public String ShowExample(){
        //Using this app to send a GET request to /JoshFruit/Example of Spring-REST-Build app
        HttpHeaders headers = new HttpHeaders(); 
        
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        
        HttpEntity<String> entity = new HttpEntity<String>(headers); 
        
        return restTemplate.exchange("http://localhost:9090/JoshFruit/Example?str=StringFromQueryParams&int=10", HttpMethod.GET, entity, String.class).getBody(); 
    }    
    
    
    @GetMapping(path = "/Fruit")  
    public String getFruit(){
        //Using this app to send a GET request to /JoshFruit/Fruit of Spring-REST-Build app
        HttpHeaders headers = new HttpHeaders(); 
        
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        
        HttpEntity<String> entity = new HttpEntity<String>(headers); 
        
        return restTemplate.exchange("http://localhost:9090/JoshFruit/Fruit", HttpMethod.GET, entity, String.class).getBody(); 
    }  
    
    @GetMapping(path = "/Fruit/{T1}/{T2}") 
    public String getFruitX(@PathVariable("T1") Integer T1, @PathVariable("T2") Integer T2){
    //Using this app to send a GET request to /JoshFruit/Fruit/{T}/{T} of Spring-REST-Build app
        HttpHeaders headers = new HttpHeaders(); 
        
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        
        HttpEntity<String> entity = new HttpEntity<String>(headers); 
        
        String requestUrl = "http://localhost:9090/JoshFruit/Fruit/"+T1+"/"+T2;
        
        return restTemplate.exchange(requestUrl, HttpMethod.GET, entity, String.class).getBody(); 
        //
    }
    
    
    @PostMapping(path = "/Create/{T1}")  
    public String createFruit(@RequestBody Fruit fruitp, @PathVariable("T1") Integer T1){
    //Using this app to send a POST request to /JoshFruit/Fruit/Create/{T} of Spring-REST-Build app
        HttpHeaders headers = new HttpHeaders(); 
        
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON)); 
        
        HttpEntity<Fruit> entity = new HttpEntity<Fruit>(fruitp,headers); 
        
        String requestUrl = "http://localhost:9090/JoshFruit/Fruit/Create/"+T1;
        
        return restTemplate.exchange(requestUrl, HttpMethod.POST, entity, String.class).getBody(); 
    }    
    
    @PutMapping(path = "/Update/{T1}")
    public String update(@PathVariable("T1") Integer T1, @RequestBody Fruit fruitp){
    //Using this app to send a PUT request to /JoshFruit/Fruit/Update/{T} of Spring-REST-Build app
        HttpHeaders headers = new HttpHeaders(); 
        
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON)); 
        
        HttpEntity<Fruit> entity = new HttpEntity<Fruit>(fruitp,headers); 
        
        String requestUrl = "http://localhost:9090/JoshFruit/Fruit/Update/"+T1;
        
        return restTemplate.exchange(requestUrl, HttpMethod.PUT, entity, String.class).getBody(); 
    }

    @DeleteMapping(path="/Delete/{T1}")  
    public String delete(@PathVariable("T1") String T1){
    //Using this app to send a DELETE request to /JoshFruit/Fruit/Delete/{T} of Spring-REST-Build app
        HttpHeaders headers = new HttpHeaders(); 
        
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON)); 
        
        HttpEntity<Fruit> entity = new HttpEntity<Fruit>(headers); 
        
        String requestUrl = "http://localhost:9090/JoshFruit/Fruit/Delete/"+T1;
        
        return restTemplate.exchange(requestUrl, HttpMethod.DELETE, entity, String.class).getBody(); 
    } 
    
    @RequestMapping(path = "/Exit")  
    public String Exit(){
        //Using this app to send a GET request to /JoshFruit/Exit of Spring-REST-Build app
        HttpHeaders headers = new HttpHeaders(); 
        
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        
        HttpEntity<String> entity = new HttpEntity<String>(headers); 
        
        return restTemplate.exchange("http://localhost:9090/JoshFruit/Exit", HttpMethod.GET, entity, String.class).getBody(); 
    }
}



//In order to send a GET request
//Shell: curl http://localhost:4545/ConsumeWeb/Example
//THE ABOVE IS FOR .ShowExample(..)


//In order to send a GET request
//Shell: curl http://localhost:4545/ConsumeWeb/Fruit
//THE ABOVE IS FOR .getFruit()

//In order to send a GET request 
//Shell: curl http://localhost:4545/ConsumeWeb/Fruit/1/2
//THE ABOVE IS FOR .getFruitX()


//In order to send a DELETE request
//Shell: curl -X "DELETE" http://localhost:4545/ConsumeWeb/Delete/6
//THE ABOVE IS FOR .delete(..)


//In order to send a POST request
//Shell: curl -H "Content-Type: application/json" -X POST -d '{"NumberOfFruit":25,"orange":{"NumberOfOrange":25,"Amount":2500},"apple":{"NumberOfApple":25,"Amount":12500},"lemon":{"NumberOfLemon":25,"Amount":7500}}' http://localhost:4545/ConsumeWeb/Create/6
//THE ABOVE IS FOR .createFruit(..)


//In order to send a PUT request
//Shell: curl -X PUT -d '{"NumberOfFruit":15,"orange":{"NumberOfOrange":15,"Amount":1500},"apple":{"NumberOfApple":15,"Amount":7500},"lemon":{"NumberOfLemon":15,"Amount":4500}}' -H "Content-Type: application/json" http://localhost:4545/ConsumeWeb/Update/6
//THE ABOVE IS FOR .update(..)


//In order to exist this program:
//Shell: curl http://localhost:4545/ConsumeWeb/Exit