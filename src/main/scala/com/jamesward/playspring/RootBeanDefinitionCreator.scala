package com.jamesward.playspring

import org.springframework.beans.factory.config.BeanDefinition
import org.springframework.beans.factory.support.RootBeanDefinition

/**
 * This class is a hack (defined in the spring package to access the package private constructor)
 *
 * If we were to subclass the bean factory, it wouldn't be needed, as it provides protected methods that could be used.
 */
object RootBeanDefinitionCreator {

  def create(bd: RootBeanDefinition): RootBeanDefinition = new RootBeanDefinition(bd)

}
