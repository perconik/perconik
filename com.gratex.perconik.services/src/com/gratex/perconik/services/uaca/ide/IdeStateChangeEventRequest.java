package com.gratex.perconik.services.uaca.ide;

public class IdeStateChangeEventRequest extends BaseIdeEventRequest {
	/**
	 * Uri specifying state/perspective to which has been IDE switched. 
	 * It should be in form of:
	 * http://perconik.gratex.com/useractivity/enum/idestatechangeevent/statetype/[idename]#[value] where value is name of the state/perspective ("debug", "build", ..) 
	 * and ideName is "eclipse", "vs",
	 */
	private String stateTypeUri;

	/**
	 * @return the {@link #stateTypeUri}
	 */
	public String getStateTypeUri() {
		return stateTypeUri;
	}

	/**
	 * @param {@link #stateTypeUri}
	 */
	public void setStateTypeUri(String stateTypeUri) {
		this.stateTypeUri = stateTypeUri;
	}
}