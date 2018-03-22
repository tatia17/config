package config.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import config.model.ConfigEntity;
import config.service.ConfigService;

@Controller
@RequestMapping("/configs")
@SuppressWarnings("rawtypes")
public class ConfigController {

	@Autowired(required = true)
	private ConfigService configService;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView findNews() {

		Map<String, Object> model = new HashMap<String, Object>();
		List<ConfigEntity> configs = configService.findConfigs();

		model.put("configs", configs);
		return new ModelAndView("config_list", "configs", model);

	}

	@RequestMapping(value = "new", method = RequestMethod.GET)
	public ModelAndView newConf(@RequestParam MultiValueMap parameters, ModelMap model, HttpServletRequest request)
			throws IOException {

		Map<String, Object> modell = new HashMap<String, Object>();
		try {
			return new ModelAndView("config_editor", "configs", modell);
		} catch (Exception ex) {
			ex.printStackTrace();
			return new ModelAndView("error");
		}
	}

	@RequestMapping(value = "edit/{id}", method = RequestMethod.GET)
	public ModelAndView ConfEdit(@ModelAttribute("user") @PathVariable(value = "id") Integer id,
			@RequestParam MultiValueMap parameters, ModelMap model, HttpServletRequest request) throws IOException {

		Map<String, Object> modell = new HashMap<String, Object>();
		ConfigEntity configs = configService.getConfigEntity(id);

		modell.put("configs", configs);
		try {
			return new ModelAndView("config_editor", "configs", modell);
		} catch (Exception ex) {
			ex.printStackTrace();
			return new ModelAndView("error");
		}
	}

	@RequestMapping(value = "save_confs", method = RequestMethod.POST)
	public ModelAndView saveNews(@RequestParam(value = "confTitle", required = false) String confName,
			@RequestParam(value = "active", required = false) Boolean active,
			@RequestParam(value = "id", required = false) Integer id) {

		try {

			ConfigEntity config = new ConfigEntity();

			config.setConfName(confName);
			if (active != null) {
				config.setActive(active);
			}
			

			if (id != null) {
				config.setId(id);
				
				configService.updateConfig(config);

			} else {
				id = configService.saveConfig(config);
			}

			return new ModelAndView("redirect:" + "/configs");
		} catch (Exception ex) {

			ex.printStackTrace();
			return new ModelAndView("/error");
		}

	}

	@RequestMapping(value = "remove", method = RequestMethod.GET)
	public ModelAndView configDelete(@RequestParam(value = "id", required = false) Integer id) {

		try {
			configService.removeConfig(id);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return new ModelAndView("redirect:" + "/label");
	}

}
