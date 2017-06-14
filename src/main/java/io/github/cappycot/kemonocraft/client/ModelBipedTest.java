package io.github.cappycot.kemonocraft.client;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class ModelBipedTest extends ModelBiped {
	
	public ModelBipedTest()
    {
        this(0.0F);
    }

    public ModelBipedTest(float p_i1148_1_)
    {
        this(p_i1148_1_, 0.0F, 64, 32);
        
    }
    
    public ModelBipedTest(float p_i1149_1_, float p_i1149_2_, int p_i1149_3_, int p_i1149_4_)
    {
    	super(p_i1149_1_, p_i1149_2_, p_i1149_3_, p_i1149_4_);
    }
	
	

}
