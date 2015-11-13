package commons.spring.mvc.view;

import java.util.Map;

import org.springframework.web.servlet.view.AbstractUrlBasedView;
import org.springframework.web.servlet.view.velocity.VelocityLayoutViewResolver;

import com.ec.seller.common.utils.EcUrl;

public class VelocityLayoutWithCommonToolsViewResolver extends VelocityLayoutViewResolver {
	private Map<String, Object> velocityTools;
	private Map<String, EcUrl> velocityUrl;
	
	
	public Map<String, EcUrl> getVelocityUrl() {
		return velocityUrl;
	}


	public void setVelocityUrl(Map<String, EcUrl> velocityUrl) {
		this.velocityUrl = velocityUrl;
	}


	public Map<String, Object> getVelocityTools() {
		return velocityTools;
	}


	public void setVelocityTools(Map<String, Object> velocityTools) {
		this.velocityTools = velocityTools;
	}
	
	
	@Override
	protected Class requiredViewClass() {
		return VelocityLayoutWithCommonToolsView.class;
	}
	
	
	@Override
	protected AbstractUrlBasedView buildView(String viewName) throws Exception {
		VelocityLayoutWithCommonToolsView view = (VelocityLayoutWithCommonToolsView) super.buildView(viewName);
		
		if (this.velocityTools != null) {
			view.setVelocityTools(this.velocityTools);
		}
		if(this.velocityUrl != null){
			view.setVelocityUrl(velocityUrl);
		}
		return view;
	}
	
}
