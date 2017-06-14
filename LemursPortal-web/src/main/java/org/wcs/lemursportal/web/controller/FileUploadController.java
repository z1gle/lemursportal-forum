package org.wcs.lemursportal.web.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.wcs.lemursportal.service.exception.UnacceptableFileFormatException;
import org.wcs.lemursportal.web.constants.URL;

/**
 * 
 * @author admin
 *
 */
@Controller
public class FileUploadController {

	
	private static final String FORMATION_IMAGES_PATH = "images/upload/";
	private static final String JPG_CONTENT_TYPE = "image/jpeg";
	private static final String PNG_CONTENT_TYPE = "image/png";
	
	@Autowired ServletContext context;

	/**
	 * Upload image
	 */
	@RequestMapping(method = RequestMethod.POST, value = URL.UPLOAD_IMAGE)
	@ResponseBody
	public String uploadimage(@RequestParam("file") MultipartFile image)
			throws IOException {

		String imageName = Calendar.getInstance().getTimeInMillis()
				+ image.getOriginalFilename();

		if (!image.isEmpty()) {
			String imageType = image.getContentType();
			if (!(imageType.equals(JPG_CONTENT_TYPE) || imageType
					.equals(PNG_CONTENT_TYPE))) {

				throw new UnacceptableFileFormatException();
			}

			String fileLocation = context.getRealPath("/resources/") + FORMATION_IMAGES_PATH;
			File file = new File(fileLocation + imageName);
			FileUtils.writeByteArrayToFile(file, image.getBytes());
		}

		return "u-images/" + imageName;
	}

	/**
	 * Get image from image_path
	 */
	@RequestMapping(method = RequestMethod.GET, value = URL.SHOW_IMAGE)
	public void showImg(@PathVariable("name") String imageName,
			@PathVariable("type") String type, HttpServletResponse response)
			throws IOException {
		String fileLocation = context.getRealPath("/resources/") + FORMATION_IMAGES_PATH;
		try (InputStream in = new FileInputStream(fileLocation
				+ imageName + "." + type)) {
			FileCopyUtils.copy(in, response.getOutputStream());
		}
	}
}
