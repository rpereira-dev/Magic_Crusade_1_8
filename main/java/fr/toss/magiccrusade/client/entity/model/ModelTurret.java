package fr.toss.magiccrusade.client.entity.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class ModelTurret extends ModelBase
{
  ModelRenderer pied5;
  ModelRenderer pied4;
  ModelRenderer pied3;
  ModelRenderer pied2;
  ModelRenderer pied;
  ModelRenderer canon3;
  ModelRenderer canon2;
  ModelRenderer canon;

  public ModelTurret()
  {
	  this.textureWidth  = 128;
	  this.textureHeight = 64;
   
	  this.pied5 = new ModelRenderer(this, 0, 49);
	  this.pied5.addBox(-7.0F, 0.0F, -7.0F, 14, 1, 14);
	  this.pied5.setRotationPoint(0.0F, 23.0F, 0.0F);
	  this.pied5.setTextureSize(128, 64);
	  this.pied5.mirror = true;
	  setRotation(this.pied5, 0.0F, 0.0F, 0.0F);
	  this.pied4 = new ModelRenderer(this, 0, 36);
	  this.pied4.addBox(-6.0F, -1.0F, -6.0F, 12, 1, 12);
	  this.pied4.setRotationPoint(0.0F, 23.0F, 0.0F);
	  this.pied4.setTextureSize(128, 64);
	  this.pied4.mirror = true;
	  setRotation(this.pied4, 0.0F, 0.0F, 0.0F);
	  this.pied3 = new ModelRenderer(this, 0, 27);
	  this.pied3.addBox(-4.0F, -2.0F, -4.0F, 8, 1, 8);
	  this.pied3.setRotationPoint(0.0F, 23.0F, 0.0F);
	  this.pied3.setTextureSize(128, 64);
	  this.pied3.mirror = true;
	  setRotation(this.pied3, 0.0F, 0.0F, 0.0F);
	  this.pied2 = new ModelRenderer(this, 32, 28);
	  this.pied2.addBox(-2.0F, -6.0F, -2.0F, 4, 4, 4);
	  this.pied2.setRotationPoint(0.0F, 23.0F, 0.0F);
	  this.pied2.setTextureSize(128, 64);
	  this.pied2.mirror = true;
	  setRotation(this.pied2, 0.0F, 0.0F, 0.0F);
	  this.pied = new ModelRenderer(this, 48, 31);
	  this.pied.addBox(-1.0F, -18.0F, -1.0F, 2, 16, 2);
	  this.pied.setRotationPoint(0.0F, 23.0F, 0.0F);
	  this.pied.setTextureSize(128, 64);
	  this.pied.mirror = true;
	  setRotation(this.pied, 0.0F, 0.0F, 0.0F);
	  this.canon3 = new ModelRenderer(this, 0, 12);
	  this.canon3.addBox(-2.0F, -2.0F, -2.0F, 4, 4, 4);
	  this.canon3.setRotationPoint(0.0F, 8.0F, 0.0F);
	  this.canon3.setTextureSize(128, 64);
	  this.canon3.mirror = true;
	  setRotation(this.canon3, 0.0F, 0.0F, 0.0F);
	  this.canon2 = new ModelRenderer(this, 0, 4);
	  this.canon2.addBox(-1.5F, -1.5F, -7.0F, 3, 3, 5);
	  this.canon2.setRotationPoint(0.0F, 8.0F, 0.0F);
	  this.canon2.setTextureSize(128, 64);
	  this.canon2.mirror = true;
	  setRotation(this.canon2, 0.0F, 0.0F, 0.0F);
	  this.canon = new ModelRenderer(this, 0, 0);
	  this.canon.addBox(-1.0F, -1.0F, -15.0F, 2, 2, 20);
	  this.canon.setRotationPoint(0.0F, 8.0F, 0.0F);
	  this.canon.setTextureSize(128, 64);
	  this.canon.mirror = true;
	  setRotation(this.canon, 0.0F, 0.0F, 0.0F);
  }

  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
	  super.render(entity, f, f1, f2, f3, f4, f5);
	  this.setRotationAngles(f, f1, f2, f3, f4, f5);
	  this.pied5.render(f5);
	  this.pied4.render(f5);
	  this.pied3.render(f5);
	  this.pied2.render(f5);
	  this.pied.render(f5);
	  this.canon3.render(f5);
	  this.canon2.render(f5);
	  this.canon.render(f5);
  }

  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
	  model.rotateAngleX = x;
	  model.rotateAngleY = y;
	  model.rotateAngleZ = z;
  }

  public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5)
  {
	  this.canon.rotateAngleX = (MathHelper.cos(f * 0.6662F) * 1.4F * f1);
	  this.canon2.rotateAngleX = (-MathHelper.cos(f * 0.6662F) * 1.4F * f1);
	  this.canon2.rotateAngleY = (-MathHelper.cos(f * 0.6662F) * 1.4F * f1);
	  super.setRotationAngles(f, f1, f2, f3, f4, f5, null);
  }

}