package link.ww.third.service;

import link.ww.third.ThirdProperties;
import link.ww.third.cache.SuiteTicketCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SuiteTicketService {

  @Autowired
  private ThirdProperties thirdProperties;

  @Autowired
  private SuiteTicketCache suiteTicketCache;

  public void setTicket(String ticket) {
    suiteTicketCache.put(thirdProperties.getSuiteId(), ticket);
  }

  public String getTicket() {
    return suiteTicketCache.get(thirdProperties.getSuiteId());
  }

}
