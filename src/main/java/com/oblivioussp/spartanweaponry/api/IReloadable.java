package com.oblivioussp.spartanweaponry.api;

/**
 * Interface used for reloading values when Tags are updated. Allows for redefining which Weapon Traits are on weapons<br>
 * The instance using this interface will need to be manually added to the reload list via {@link ReloadableHandler#addToReloadList(IReloadable)} for this to work
 * @author ObliviousSpartan
 *
 */
public interface IReloadable
{
	public void reload();
}
