package com.taller4.backend.service.interfaces;

import com.taller4.backend.model.sales.Specialoffer;

public interface SpecialofferService {
	public Specialoffer saveSpecialOffer(Specialoffer s) throws Exception;
	public Specialoffer searchSpecialOffer(Integer sId);
	public Specialoffer updateSpecialOffer(Integer sId, Specialoffer s) throws Exception;
	public void deleteSpecialOffer(Integer sId);
}
