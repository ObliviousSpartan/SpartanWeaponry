package com.oblivioussp.spartanweaponry.util;

import net.minecraft.util.math.Vec3d;

/**
 * My own half-assed implementation of a Quaternion (only implemented what is required to do a rotation transform of two vectors with an angle)
 * @author ObliviousSpartan
 *
 */
public class Quaternion
{
	private float x, y, z, w;
	
	public Quaternion(float x, float y, float z, float w)
	{
		this.x = x;
		this.y = y;
		this.z = z;
		this.w = w;
	}
	
	public Quaternion(Quaternion quat)
	{
		this.x = quat.x;
		this.y = quat.y;
		this.z = quat.z;
		this.w = quat.w;
	}
	
	public Quaternion(Vec3d vector, float angle, boolean degrees)
	{
		if(degrees)
			angle *= (Math.PI / 180.0d);
		
		float mag = (float)Math.sin((double)angle / 2.0f);
		x = (float)vector.x * mag;
		y = (float)vector.y * mag;
		z = (float)vector.z * mag;
		w = (float)Math.cos((double)angle / 2);
	}
	
	public void multiply(Quaternion quat)
	{
		float xr = w * quat.x + x * quat.w + y * quat.z - z * quat.y;
		float yr = w * quat.y - x * quat.z + y * quat.w + z * quat.x;
		float zr = w * quat.z + x * quat.y - y * quat.x + z * quat.w;
		float wr = w * quat.w - x * quat.x - y * quat.y - z * quat.z;
		
		x = xr;
		y = yr;
		z = zr;
		w = wr;
	}
	
	public void conjugate()
	{
		x = -x;
		y = -y;
		z = -z;
	}
	
	public Vec3d transformVector(Vec3d vector)
	{
		Quaternion resultQuat = new Quaternion(this);
		Quaternion quatVec = new Quaternion((float)vector.x, (float)vector.y, (float)vector.z, 0.0f);
		
		// Multiply both quaternions together
		resultQuat.multiply(quatVec);
		// Get a conjugated copy of this quaternion multiply the conjugal and result quaternion
		Quaternion conjQuat = new Quaternion(this);
		conjQuat.conjugate();
		resultQuat.multiply(conjQuat);
		
		return new Vec3d(resultQuat.x, resultQuat.y, resultQuat.z);
	}
}
