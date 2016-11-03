package Main.controller;

import Main.model.*;
import Main.validator.FileValidator;
import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;
import org.springframework.web.servlet.view.RedirectView;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Controller
@SessionAttributes("game")
public class Main {
    @Autowired
    FileValidator fileValidator;
    @Autowired
    ImageDAO sqLiteDAO;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String indexPage(ModelMap model) {

        model.addAttribute("message", "Hello Guest, this is the Home Page...");
        return "index";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage() {
        return "login";
    }

    //-------------------------------

    //tranzaction
   // @PreAuthorize("hasAnyRole('ROLE_MEMBER','ROLE_ADMIN')")
    @RequestMapping("/delete")
    public ModelAndView deleteImage(@RequestParam String id) {
        sqLiteDAO.delete(Integer.parseInt(id));
        List<ImageDataSet> lists=sqLiteDAO.getAllImageDataSet();
        return new ModelAndView("allImages","lists",lists);
    }


    // @PreAuthorize("hasAnyRole('ROLE_MEMBER','ROLE_ADMIN')")

    @RequestMapping("/game")
    public ModelAndView gameGuessImage(@ModelAttribute Game game,@RequestParam(value = "answer", required=false) String answer, RedirectAttributes redirectAttributes) {//need prg pattern
        ModelAndView modelAndView = new ModelAndView();
        if(answer != null){
            redirectAttributes.addFlashAttribute(answer);
            System.out.println(answer);
            game.setCurrentStep(game.getCurrentStep() + 1);
            if (game.getTrueAnswer().equals(answer)){
                game.setTrueAnswerCount(game.getTrueAnswerCount()+1);
            }
        }
        if (game.isEndGame()){
            return new ModelAndView("index");
        }
        if (game.getCurrentStep()==game.getCountStep()){
            game.setEndGame(true);
            game.setImage(null);
            RedirectView redirectView = new RedirectView("endPage");
            redirectView.setStatusCode(HttpStatus.MOVED_PERMANENTLY);
            modelAndView.setView(redirectView);
            redirectAttributes.addFlashAttribute("trueAnswerCount",game.getTrueAnswerCount());
            return modelAndView;
        }else {
            RedirectView redirectView = new RedirectView("gamePage");
            redirectView.setStatusCode(HttpStatus.MOVED_PERMANENTLY);
            modelAndView.setView(redirectView);
            System.out.println("Step "+game.getCurrentStep());
            Random random = new Random();
            List<Integer> arrayList=sqLiteDAO.getAllID();
            ImageDataSet imageDataSetForShow = sqLiteDAO.getImageDataSetByID(arrayList.get(random.nextInt(arrayList.size())));
            ImageDataSet imageDataSetForName = sqLiteDAO.getImageDataSetByID(arrayList.get(random.nextInt(arrayList.size())));
            game.setTrueAnswer(imageDataSetForShow.getName());
            game.setFalseAnswer(imageDataSetForName.getName());
            game.setImage(imageDataSetForShow.getImageForShow());
            redirectAttributes.addFlashAttribute("game", game);
            return modelAndView;
        }
    }
    @RequestMapping(value = "/gamePage", method = RequestMethod.GET)
    public ModelAndView goGamePage() {
        return new ModelAndView("gamePages");
    }
    @RequestMapping(value = "/endPage", method = RequestMethod.GET)
    public ModelAndView goEndPage() {
        return new ModelAndView("endGame");
    }

    // @PreAuthorize("hasAnyRole('ROLE_MEMBER','ROLE_ADMIN')")
    @RequestMapping("/gameLaunch")
    public ModelAndView launchGame() {

        if (sqLiteDAO.getAllImageDataSet().size()<5){
            return new ModelAndView("notEnoughImages");
        }else {
            return new ModelAndView("gameLaunch","game",new Game());
        }

    }


    //@PreAuthorize("hasAnyRole('ROLE_MEMBER','ROLE_ADMIN')")
    @RequestMapping("/uploadForm")
    public ModelAndView getUploadForm(@ModelAttribute("uploadedFile") UploadedFile uploadedFile) {
        return new ModelAndView("uploadForm");
    }

    //@PreAuthorize("hasAnyRole('ROLE_MEMBER','ROLE_ADMIN')")
    @RequestMapping("/check")
    public ModelAndView getAllImages(){
        List<ImageDataSet> lists=sqLiteDAO.getAllImageDataSet();
        return new ModelAndView("allImages","lists",lists);
    }
    //@PreAuthorize("hasAnyRole('ROLE_MEMBER','ROLE_ADMIN')")
    @RequestMapping("/fileUpload")
    public ModelAndView fileUploaded(@ModelAttribute("uploadedFile") UploadedFile uploadedFile, BindingResult result) {
        MultipartFile file = uploadedFile.getFile();
        fileValidator.validate(uploadedFile, result);
        ImageDataSet imageDataSet = null;
        String name=uploadedFile.getName();
        if (result.hasErrors()) {
            return new ModelAndView("uploadForm");
        }

        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                imageDataSet=new ImageDataSet();
                imageDataSet.setName(name);
                imageDataSet.setData(bytes);
                sqLiteDAO.insert(imageDataSet);
            } catch (Exception e) {

            }
        }

        return new ModelAndView("showFile", "message", imageDataSet.getImageForShow());
    }
}