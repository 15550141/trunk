package commons.spring.mvc.view;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.velocity.context.Context;
import org.springframework.web.servlet.view.velocity.VelocityLayoutView;

import com.ec.seller.common.utils.EcUrl;

public class VelocityLayoutWithCommonToolsView extends VelocityLayoutView {
	
	private Map<String, Object> velocityTools;
	private Map<String, EcUrl> velocityUrl;
	
	/**
	 * 将tools、url中的内容合并到上下文中
	 * 先合url，再合tools，如果里面已经存在，则不变
	 *
	 * @param context
	 */
	private void mergeContext(Context context) {
		mergeUrl(context, velocityUrl);
		merge(context, velocityTools);
	}
	
	
	private void merge(Context context, Map<String, Object> map) {
		if (map != null) {
			for (Map.Entry<String, Object> stringObjectEntry : map.entrySet()) {
				context.put(stringObjectEntry.getKey(), stringObjectEntry.getValue());
			}
		}
	}
	
	private void mergeUrl(Context context, Map<String, EcUrl> map) {
		if (map != null) {
			for (Map.Entry<String, EcUrl> stringJdUrlEntry : map.entrySet()) {
				String key = stringJdUrlEntry.getKey();
				EcUrl org = stringJdUrlEntry.getValue();
				EcUrl value = org.clone();//解决多线程并发的问题。
				value.setEcUrl(org); //原始的不拿出来配置。
				context.put(key, value);
			}
		}
	}
	


	@Override
	protected void doRender(Context context, HttpServletResponse response) throws Exception {
		mergeContext(context);
		super.doRender(context, response);
	}


	public Map<String, Object> getVelocityTools() {
		return velocityTools;
	}


	public void setVelocityTools(Map<String, Object> velocityTools) {
		this.velocityTools = velocityTools;
	}


	public Map<String, EcUrl> getVelocityUrl() {
		return velocityUrl;
	}


	public void setVelocityUrl(Map<String, EcUrl> velocityUrl) {
		this.velocityUrl = velocityUrl;
	}
}

