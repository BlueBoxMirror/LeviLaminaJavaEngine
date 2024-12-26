package bluebox.ll.worlds;

public class Entity {
    protected Entity(){}
    protected long nativePtr=0;

    public native String getName();
    public native String getType();
    public native int getId();
    public native FloatPos getPosition();
    public native FloatPos getFeetPosition();
    public native IntPos getBlockPosition();
    public native int getMaxHealth();
    public native int getHealth();
    public native void setHealth(int health);
    public native void damage(int damage);
    public native void kill();
    public native boolean canFly();
    public native boolean canFreeze();
    public native boolean canSeeDaylight();
    public native boolean canPickupItems();
    public native boolean isInAir();
    public native boolean isInWater();
    public native boolean isInLava();
    public native boolean isInRain();
    public native boolean isInSnow();
    public boolean isInWaterOrRain(){
        return isInWater() || isInRain();
    }
    public native boolean isInWorld();
    public native float getSpeed();
    public native Angle getDirection();
    public native String getUniqueId();
    public native boolean isVisible();
    public native boolean isInsidePortal();
    public native boolean isTrusting();
    public native boolean isTouchingDamageBlock();
    public native boolean isOnFire();
    public native boolean isOnGround();
    public native boolean isOnHotBlock();
    public native boolean isTrading();
    public native boolean isRiding();
    public native boolean isDancing();
    public native boolean isSleeping();
    public native boolean isAngry();
    public native boolean isBaby();
    public native boolean isMoving();
    public native void teleport(FloatPos pos, Angle angle);
    public void teleport(FloatPos pos, float yaw, float pitch){
        teleport(pos, new Angle(yaw, pitch));
    }
    public void teleport(IntPos pos, Angle angle){
        teleport(pos.toFloatPos(), angle);
    }
    public void teleport(IntPos pos, float yaw, float pitch) {
        teleport(pos.toFloatPos(), new Angle(yaw, pitch));
    }
    public void teleport(FloatPos pos){
        teleport(pos, getDirection());
    }
    public void teleport(IntPos pos){
        teleport(pos.toFloatPos(), getDirection());
    }
    public void teleport(float x, float y, float z, int dimension, float yaw, float pitch){
        teleport(new FloatPos(x, y, z,dimension), new Angle(yaw, pitch));
    }
    public void teleport(float x, float y, float z, int dimension){
        teleport(new FloatPos(x, y, z,dimension), getDirection());
    }
    public void teleport(float x, float y, float z){
        teleport(new FloatPos(x, y, z,getPosition().dimension), getDirection());
    }
    public void teleport(float x,float y,float z,float yaw, float pitch){
        teleport(new FloatPos(x, y, z,getPosition().dimension), new Angle(yaw, pitch));
    }
    public void teleport(int x,int y,int z,int dimension,float yaw,float pitch){
        teleport(new IntPos(x, y, z,dimension), new Angle(yaw, pitch));
    }
    public void teleport(int x,int y,int z,int dimension){
        teleport(new IntPos(x, y, z,dimension), getDirection());
    }
    public void teleport(int x,int y,int z){
        teleport(new IntPos(x, y, z,getPosition().dimension), getDirection());
    }
    public void teleport(int x,int y,int z,float yaw,float pitch){
        teleport(new IntPos(x, y, z,getPosition().dimension), new Angle(yaw, pitch));
    }
    public native void despawn();
    public native void remove();
    public native void hurt(int damage,ActorDamageCause cause,Entity source);
    public void hurt(int damage){
        hurt(damage, ActorDamageCause.ALL, null);
    }
    public native void heal(int health);
    public native void setAbsorption(int value);
    public native void setAttackDamage(int value);
    public native void setMaxAttackDamage(int value);
    public native void setFollowRange(int value);
    public native void setKnockbackResistance(int value);
    public native void setLuck(int value);
    public native void setMovementSpeed(int value);
    public native void setLavaMovementSpeed(int value);
    public native void setMaxHealth(int value);
    public native void setFire(int time,boolean hasParticles);
    public void setFire(int time){
        setFire(time, true);
    }
    public native void stopFire();
    public native void setScale(float value);


}
