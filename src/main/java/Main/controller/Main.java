package Main.controller;

import Main.model.ImageDAO;
import Main.model.ImageDataSet;
import Main.model.UploadedFile;
import Main.validator.FileValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

@Controller
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
        File file=new File(sqLiteDAO.getImageDataSetByID(Integer.parseInt(id)).getURL());
        System.out.println(sqLiteDAO.getImageDataSetByID(Integer.parseInt(id)).getURL());
        file.delete();
        File file1=new File("/ImageSecurity_war_exploded/img/777.jpg");
        sqLiteDAO.delete(Integer.parseInt(id));
        List<ImageDataSet> lists=sqLiteDAO.getAllImageDataSet();
        return new ModelAndView("allImages","lists",lists);
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
    public ModelAndView fileUploaded(@ModelAttribute("uploadedFile") UploadedFile uploadedFile,HttpServletRequest request, BindingResult result) {
        MultipartFile file = uploadedFile.getFile();
        fileValidator.validate(uploadedFile, result);
        String fileName = file.getOriginalFilename();
        String name=uploadedFile.getName();
        if (result.hasErrors()) {
            return new ModelAndView("uploadForm");
        }

        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();
                String rootPath = request.getSession().getServletContext().getRealPath("/");
                File dir = new File(rootPath + File.separator + "img");
                if (!dir.exists()) {
                    dir.mkdirs();
                }
                File uploadFile = new File(dir.getAbsolutePath() + File.separator + fileName);
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(uploadFile));
                stream.write(bytes);
                stream.flush();
                stream.close();
                String URL=request.getContextPath()+"/img/"+fileName;
                ImageDataSet imageDataSet=new ImageDataSet();
                imageDataSet.setName(name);
                imageDataSet.setURL(URL);
                System.out.println("trololo");
                sqLiteDAO.insert(imageDataSet);
            } catch (Exception e) {

            }
        }
        return new ModelAndView("showFile", "message", fileName);
    }
}