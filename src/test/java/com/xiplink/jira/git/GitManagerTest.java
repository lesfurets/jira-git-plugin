package com.xiplink.jira.git;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.eclipse.jgit.lib.Constants;
import org.eclipse.jgit.revwalk.RevCommit;
import org.w3c.dom.Document;

import com.opensymphony.module.propertyset.PropertyException;
import com.opensymphony.module.propertyset.PropertySet;
import com.opensymphony.module.propertyset.PropertySetSchema;

public class GitManagerTest {

    public void testRepository() {
//      String path = "src/git/jira/.git";
//      File root = new File(path);
//      RepositoryBuilder builder = new RepositoryBuilder().addCeilingDirectory(root).findGitDir(root);
//      if (builder.getGitDir() == null) {
//          builder.setGitDir(root);
//      }
//
//      Repository repository = builder.build();
//      System.out.println(repository.getObjectsDirectory().exists());
//
//      repository.scanForRepoChanges();
//      for (Map.Entry<String, Ref> pair : repository.getAllRefs().entrySet()) {
//          System.out.println(pair.getKey());
//      }
//      System.out.println(repository.getRef("HEAD"));
    }
    
    public static void main(String[] args) throws Exception {
        
        final PropertySet props = new PropertySet() {
            
            Map<String, Object> map = new HashMap<String, Object>();
            public boolean supportsTypes() {
                return true;
            }
            
            public boolean supportsType(int arg0) {
                return true;
            }
            
            public void setXML(String arg0, Document arg1) throws PropertyException {
                map.put(arg0, arg1);
            }
            
            public void setText(String arg0, String arg1) throws PropertyException {
                map.put(arg0, arg1);
            }
            
            public void setString(String arg0, String arg1) throws PropertyException {
                map.put(arg0, arg1);
            }
            
            PropertySetSchema propertySetSchema;
            public void setSchema(PropertySetSchema arg0) throws PropertyException {
                propertySetSchema = arg0;
            }
            
            public void setProperties(String arg0, Properties arg1) throws PropertyException {
                map.put(arg0, arg1);
            }
            
            public void setObject(String arg0, Object arg1) throws PropertyException {
                map.put(arg0, arg1);
            }
            
            public void setLong(String arg0, long arg1) throws PropertyException {
                map.put(arg0, arg1);
            }
            
            public void setInt(String arg0, int arg1) throws PropertyException {
                map.put(arg0, arg1);
            }
            
            public void setDouble(String arg0, double arg1) throws PropertyException {
                map.put(arg0, arg1);
            }
            
            public void setDate(String arg0, Date arg1) throws PropertyException {
                map.put(arg0, arg1);
            }
            
            public void setData(String arg0, byte[] arg1) throws PropertyException {
                map.put(arg0, arg1);
            }
            
            public void setBoolean(String arg0, boolean arg1) throws PropertyException {
                map.put(arg0, arg1);
            }
            
            public void setAsActualType(String arg0, Object arg1) throws PropertyException {
                map.put(arg0, arg1);
            }
            
            public void remove(String arg0) throws PropertyException {
                map.remove(arg0);
            }
            
            public void remove() throws PropertyException {
            }
            
            public boolean isSettable(String arg0) {
                return true;
            }
            
            public void init(Map arg0, Map arg1) {
                throw new RuntimeException("Not implemented");
            }
            
            public Document getXML(String arg0) throws PropertyException {
                return (Document) map.get(arg0);
            }
            
            public int getType(String arg0) throws PropertyException {
                return (Integer) map.get(arg0);
            }
            
            public String getText(String arg0) throws PropertyException {
                return (String) map.get(arg0);
            }
            
            public String getString(String arg0) throws PropertyException {
                return (String) map.get(arg0);
            }
            
            public PropertySetSchema getSchema() throws PropertyException {
                return propertySetSchema;
            }
            
            public Properties getProperties(String arg0) throws PropertyException {
                return (Properties) map.get(arg0);
            }
            
            public Object getObject(String arg0) throws PropertyException {
                return (Object) map.get(arg0);
            }
            
            public long getLong(String arg0) throws PropertyException {
                return (Long) map.get(arg0);
            }
            
            public Collection getKeys(String arg0, int arg1) throws PropertyException {
                return map.keySet();
            }
            
            public Collection getKeys(String arg0) throws PropertyException {
                return map.keySet();
            }
            
            public Collection getKeys(int arg0) throws PropertyException {
                return map.keySet();
            }
            
            public Collection getKeys() throws PropertyException {
                return map.keySet();
            }
            
            public int getInt(String arg0) throws PropertyException {
                return (Integer) map.get(arg0);
            }
            
            public double getDouble(String arg0) throws PropertyException {
                return (Double) map.get(arg0);
            }
            
            public Date getDate(String arg0) throws PropertyException {
                return (Date) map.get(arg0);
            }
            
            public byte[] getData(String arg0) throws PropertyException {
                return null;
            }
            
            public boolean getBoolean(String arg0) throws PropertyException {
                return (Boolean) map.get(arg0);
            }
            
            public Object getAsActualType(String arg0) throws PropertyException {
                return map.get(arg0);
            }
            
            public boolean exists(String arg0) throws PropertyException {
                return map.containsKey(arg0);
            }
        };
        
        props.setString(MultipleGitRepositoryManager.GIT_ROOT_KEY, "src/git/jira/.git");
        props.setBoolean(MultipleGitRepositoryManager.GIT_REVISION_INDEXING_KEY, true);
        props.setInt(MultipleGitRepositoryManager.GIT_REVISION_CACHE_SIZE_KEY, 10000);
        GitManagerImpl gitManager = new GitManagerImpl(1, props);

        String headId = gitManager.getRefId(Constants.HEAD);
        
        final Map<String, String> branches = gitManager.getBranches();
        for(String br : branches.keySet()){
            final String branchId = branches.get(br);
            RevCommit base = gitManager.getMergeBase(headId, branchId);
            String latestIndexedRevision = (base != null ? base.getId().getName() : null);
            System.out.println("+ " + br + " : " + branchId + " (last on trunk = " + latestIndexedRevision +" ) ");
            Collection<RevCommit> logEntries = gitManager.getLogEntries(latestIndexedRevision, branchId);
            for(RevCommit commit : logEntries) {
                String logMessageUpperCase = commit.getFullMessage();
                System.out.println("++ " + logMessageUpperCase);
            }
        }
        System.out.println("origin: " + gitManager.getOrigin());
        System.out.println("headId: " + headId);
    }
}
