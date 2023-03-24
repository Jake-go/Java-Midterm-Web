package ca.sheridancollege.gollej.controllers;

import ca.sheridancollege.gollej.beans.Phonecase;
import ca.sheridancollege.gollej.beans.Police;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.client.RestTemplate;

@Controller
public class MasterController {

    final private String POLICE_REST_URL = "http://localhost:8085/police";
    final private String PHONECASE_REST_URL = "http://localhost:8085/phonecase";

    @GetMapping("/")
    public String landingPage() {
        return "index.html";
    }

    @GetMapping("/police")
    public String policePage(Model model, RestTemplate restTemplate) {
        ResponseEntity<Police[]> responseEntity = restTemplate.getForEntity(POLICE_REST_URL, Police[].class);
        model.addAttribute("polices", responseEntity.getBody());
        return "police.html";
    }

    @GetMapping("/phonecase")
    public String phoneCasePage(Model model, RestTemplate restTemplate) {
        ResponseEntity<Phonecase[]> phoneResponseEntity = restTemplate.getForEntity(PHONECASE_REST_URL,
                Phonecase[].class);
        model.addAttribute("phonecases", phoneResponseEntity.getBody());
        return "phonecase.html";
    }

    @GetMapping("/addPhonecase")
    public String addPhoneCasePage(Model model, RestTemplate restTemplate) {
        ResponseEntity<Police[]> responseEntity = restTemplate.getForEntity(POLICE_REST_URL, Police[].class);
        model.addAttribute("polices", responseEntity.getBody());
        return "addphonecase.html";
    }

    @GetMapping("/processAddPolice")
    public String processAddPolice(RestTemplate restTemplate, Police police) {
        restTemplate.postForLocation(POLICE_REST_URL, police);
        return "redirect:/police";
    }

    @GetMapping("/processAddPhonecase")
    public String processAddPhonecase(RestTemplate restTemplate, Phonecase phonecase) {
        restTemplate.postForLocation(PHONECASE_REST_URL, phonecase);
        return "redirect:/phonecase";
    }

    @GetMapping("/deletePhonecase/{id}")
    public String deletePhonecase(RestTemplate restTemplate, @PathVariable Long id) {
        restTemplate.delete(PHONECASE_REST_URL + "/" + id);
        return "redirect:/phonecase";
    }

    @GetMapping("/purchasePhonecase/{id}")
    public String purchasePhonecase(RestTemplate restTemplate, @PathVariable Long id) {
        ResponseEntity<Phonecase> responseEntity = restTemplate.getForEntity(PHONECASE_REST_URL + "/" + id,
                Phonecase.class);
        Phonecase phonecase = responseEntity.getBody();
        phonecase.setQuantity(phonecase.getQuantity() - 1);
        restTemplate.put(PHONECASE_REST_URL + "/" + id, phonecase);
        return "redirect:/phonecase";
    }

}
