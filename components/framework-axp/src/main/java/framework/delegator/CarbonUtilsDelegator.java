package framework.delegator;

import org.wso2.carbon.utils.CarbonUtils;

/**
 * Delegator class for {@link CarbonUtils}
 */
public class CarbonUtilsDelegator
{

  private static final CarbonUtilsDelegator INSTANCE = new CarbonUtilsDelegator();

  public static CarbonUtilsDelegator getInstance()
  {
    return INSTANCE;
  }

  private CarbonUtilsDelegator()
  {
  }

  /**
   * Get carbon home directory path as a string
   *
   * @return
   */
  public String getCarbonHome()
  {
    return CarbonUtils.getCarbonHome();
  }

  /**
   * Get Carbon configuration directory path as a string
   *
   * @return
   */
  public String getCarbonConfigDirPath()
  {
    return CarbonUtils.getCarbonConfigDirPath();
  }
}
