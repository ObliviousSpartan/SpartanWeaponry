package com.oblivioussp.spartanweaponry.client.model;

import net.minecraft.client.model.ModelBase;

public abstract class ModelQuiverBase extends ModelBase 
{
    protected int arrowsToRender = 0;
    
	public abstract void render(float scale);

    public void setArrowsToRender(int arrowsToRender) 
    {
		this.arrowsToRender = arrowsToRender;
	}
}
