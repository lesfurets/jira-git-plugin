import java.io.File;
import java.util.Map;

import org.eclipse.jgit.lib.Ref;
import org.eclipse.jgit.lib.Repository;
import org.eclipse.jgit.lib.RepositoryBuilder;

/**
 * @author Ivan Sungurov
 */
public class RepoTest {
	public static void main(String[] args) throws Exception {
		String path = "/opt/gitblit/git/sandbox.git";
		File root = new File(path);
		RepositoryBuilder builder = new RepositoryBuilder()
				.addCeilingDirectory(root).findGitDir(root);
		if (builder.getGitDir() == null) {
			builder.setGitDir(root);
		}

		Repository repository = builder.build();
		System.out.println(repository.getObjectsDirectory().exists());

		repository.scanForRepoChanges();
		for (Map.Entry<String, Ref> pair : repository.getAllRefs().entrySet()) {
			System.out.println(pair.getKey());
		}

		System.out.println(repository.getRef("HEAD"));

//		VersionManager versionManager = mock(VersionManager.class);
//		IssueManager issueManager = mock(IssueManager.class);
//		DefaultPermissionManager permissionManager = new DefaultPermissionManager();
//		ChangeHistoryManager changeHistoryManager = mock(ChangeHistoryManager.class);
//		JiraPropertySetFactory jiraPropertySetFactory = mock(JiraPropertySetFactory.class);
//
//		PropertiesFilePropertySet propertySet = new PropertiesFilePropertySet();
//		propertySet.setLong(MultipleGitRepositoryManagerImpl.LAST_REPO_ID, 0);
//		when(
//				jiraPropertySetFactory.buildCachingDefaultPropertySet(
//						MultipleGitRepositoryManagerImpl.APP_PROPERTY_PREFIX,
//						true)).thenReturn(propertySet);
//
//		ServiceManager serviceManager = mock(ServiceManager.class);
//		IndexPathManager indexPathManager = mock(IndexPathManager.class);
//
//		MultipleGitRepositoryManager gitManager = new MultipleGitRepositoryManagerImpl(
//				versionManager, issueManager, permissionManager,
//				changeHistoryManager, jiraPropertySetFactory, serviceManager,
//				indexPathManager);
//
//		gitManager.start();
	}
}
