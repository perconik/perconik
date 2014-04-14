
package com.gratex.perconik.services.ast.rcs.entity;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;
import com.gratex.perconik.services.ast.rcs.AssociateChangesetWithBranchRequest;
import com.gratex.perconik.services.ast.rcs.EnsureBranchesRequest;
import com.gratex.perconik.services.ast.rcs.EnsureRcsProjectRequest;
import com.gratex.perconik.services.ast.rcs.EnsureRcsServerRequest;
import com.gratex.perconik.services.ast.rcs.GetBranchImportedHeadRequest;
import com.gratex.perconik.services.ast.rcs.GetBranchQueuedHeadRequest;
import com.gratex.perconik.services.ast.rcs.GetBranchRequest;
import com.gratex.perconik.services.ast.rcs.GetChangedFilesRequest;
import com.gratex.perconik.services.ast.rcs.GetChangesetRcsProjectRequest;
import com.gratex.perconik.services.ast.rcs.GetChangesetRequest;
import com.gratex.perconik.services.ast.rcs.GetChildCodeEntitiesRequest;
import com.gratex.perconik.services.ast.rcs.GetCodeEntityChangesetsRequest;
import com.gratex.perconik.services.ast.rcs.GetCodeEntityContentRequest;
import com.gratex.perconik.services.ast.rcs.GetCodeEntityFullContextRequest;
import com.gratex.perconik.services.ast.rcs.GetCodeEntityRequest;
import com.gratex.perconik.services.ast.rcs.GetFileChangesetsRequest;
import com.gratex.perconik.services.ast.rcs.GetFileContentRequest;
import com.gratex.perconik.services.ast.rcs.GetFileRequest;
import com.gratex.perconik.services.ast.rcs.GetFilesByGitIdentifiersRequest;
import com.gratex.perconik.services.ast.rcs.GetFilesByTfsIdentifiersRequest;
import com.gratex.perconik.services.ast.rcs.GetImportCapabilitiesRequest;
import com.gratex.perconik.services.ast.rcs.GetRcsProjectRequest;
import com.gratex.perconik.services.ast.rcs.GetRcsServerRequest;
import com.gratex.perconik.services.ast.rcs.GetUserRequest;
import com.gratex.perconik.services.ast.rcs.IsChangesetQueuedOrImportedRequest;
import com.gratex.perconik.services.ast.rcs.QueueChangesetForImportRequest;
import com.gratex.perconik.services.ast.rcs.SearchBranchesRequest;
import com.gratex.perconik.services.ast.rcs.SearchChangesetsRequest;
import com.gratex.perconik.services.ast.rcs.SearchCodeEntitiesRequest;
import com.gratex.perconik.services.ast.rcs.SearchFilesRequest;
import com.gratex.perconik.services.ast.rcs.SearchFoldersRequest;
import com.gratex.perconik.services.ast.rcs.SearchRcsProjectsRequest;
import com.gratex.perconik.services.ast.rcs.SearchRcsServersRequest;
import com.gratex.perconik.services.ast.rcs.SearchTypeCodeEntitiesLightRequest;
import com.gratex.perconik.services.ast.rcs.SearchUsersRequest;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.gratex.perconik.services.ast.rcs.entity package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _GetCodeEntityResponseGetCodeEntityResult_QNAME = new QName("http://www.gratex.com/PerConIk/AstRcs/IEntityService", "GetCodeEntityResult");
    private final static QName _SearchTypeCodeEntitiesLightResponseSearchTypeCodeEntitiesLightResult_QNAME = new QName("http://www.gratex.com/PerConIk/AstRcs/IEntityService", "SearchTypeCodeEntitiesLightResult");
    private final static QName _GetCodeEntityFullContextRequest_QNAME = new QName("http://www.gratex.com/PerConIk/AstRcs/IEntityService", "request");
    private final static QName _GetChangesetRcsProjectResponseGetChangesetRcsProjectResult_QNAME = new QName("http://www.gratex.com/PerConIk/AstRcs/IEntityService", "GetChangesetRcsProjectResult");
    private final static QName _EnsureBranchesResponseEnsureBranchesResult_QNAME = new QName("http://www.gratex.com/PerConIk/AstRcs/IEntityService", "EnsureBranchesResult");
    private final static QName _SearchRcsServersResponseSearchRcsServersResult_QNAME = new QName("http://www.gratex.com/PerConIk/AstRcs/IEntityService", "SearchRcsServersResult");
    private final static QName _GetChildCodeEntitiesResponseGetChildCodeEntitiesResult_QNAME = new QName("http://www.gratex.com/PerConIk/AstRcs/IEntityService", "GetChildCodeEntitiesResult");
    private final static QName _GetRcsProjectResponseGetRcsProjectResult_QNAME = new QName("http://www.gratex.com/PerConIk/AstRcs/IEntityService", "GetRcsProjectResult");
    private final static QName _GetBranchImportedHeadResponseGetBranchImportedHeadResult_QNAME = new QName("http://www.gratex.com/PerConIk/AstRcs/IEntityService", "GetBranchImportedHeadResult");
    private final static QName _QueueChangesetForImportResponseQueueChangesetForImportResult_QNAME = new QName("http://www.gratex.com/PerConIk/AstRcs/IEntityService", "QueueChangesetForImportResult");
    private final static QName _SearchUsersResponseSearchUsersResult_QNAME = new QName("http://www.gratex.com/PerConIk/AstRcs/IEntityService", "SearchUsersResult");
    private final static QName _GetChangesetResponseGetChangesetResult_QNAME = new QName("http://www.gratex.com/PerConIk/AstRcs/IEntityService", "GetChangesetResult");
    private final static QName _GetFileChangesetsResponseGetFileChangesetsResult_QNAME = new QName("http://www.gratex.com/PerConIk/AstRcs/IEntityService", "GetFileChangesetsResult");
    private final static QName _AssociateChangesetWithBranchResponseAssociateChangesetWithBranchResult_QNAME = new QName("http://www.gratex.com/PerConIk/AstRcs/IEntityService", "AssociateChangesetWithBranchResult");
    private final static QName _GetUserResponseGetUserResult_QNAME = new QName("http://www.gratex.com/PerConIk/AstRcs/IEntityService", "GetUserResult");
    private final static QName _GetFileContentResponseGetFileContentResult_QNAME = new QName("http://www.gratex.com/PerConIk/AstRcs/IEntityService", "GetFileContentResult");
    private final static QName _GetFilesByGitIdentifiersResponseGetFilesByGitIdentifiersResult_QNAME = new QName("http://www.gratex.com/PerConIk/AstRcs/IEntityService", "GetFilesByGitIdentifiersResult");
    private final static QName _EnsureRcsProjectResponseEnsureRcsProjectResult_QNAME = new QName("http://www.gratex.com/PerConIk/AstRcs/IEntityService", "EnsureRcsProjectResult");
    private final static QName _GetBranchQueuedHeadResponseGetBranchQueuedHeadResult_QNAME = new QName("http://www.gratex.com/PerConIk/AstRcs/IEntityService", "GetBranchQueuedHeadResult");
    private final static QName _SearchRcsProjectsResponseSearchRcsProjectsResult_QNAME = new QName("http://www.gratex.com/PerConIk/AstRcs/IEntityService", "SearchRcsProjectsResult");
    private final static QName _SearchBranchesResponseSearchBranchesResult_QNAME = new QName("http://www.gratex.com/PerConIk/AstRcs/IEntityService", "SearchBranchesResult");
    private final static QName _GetBranchResponseGetBranchResult_QNAME = new QName("http://www.gratex.com/PerConIk/AstRcs/IEntityService", "GetBranchResult");
    private final static QName _SearchFoldersResponseSearchFoldersResult_QNAME = new QName("http://www.gratex.com/PerConIk/AstRcs/IEntityService", "SearchFoldersResult");
    private final static QName _GetCodeEntityChangesetsResponseGetCodeEntityChangesetsResult_QNAME = new QName("http://www.gratex.com/PerConIk/AstRcs/IEntityService", "GetCodeEntityChangesetsResult");
    private final static QName _SearchFilesResponseSearchFilesResult_QNAME = new QName("http://www.gratex.com/PerConIk/AstRcs/IEntityService", "SearchFilesResult");
    private final static QName _GetCodeEntityContentResponseGetCodeEntityContentResult_QNAME = new QName("http://www.gratex.com/PerConIk/AstRcs/IEntityService", "GetCodeEntityContentResult");
    private final static QName _GetFilesByTfsIdentifiersResponseGetFilesByTfsIdentifiersResult_QNAME = new QName("http://www.gratex.com/PerConIk/AstRcs/IEntityService", "GetFilesByTfsIdentifiersResult");
    private final static QName _GetCodeEntityFullContextResponseGetCodeEntityFullContextResult_QNAME = new QName("http://www.gratex.com/PerConIk/AstRcs/IEntityService", "GetCodeEntityFullContextResult");
    private final static QName _IsChangesetQueuedOrImportedResponseIsChangesetQueuedOrImportedResult_QNAME = new QName("http://www.gratex.com/PerConIk/AstRcs/IEntityService", "IsChangesetQueuedOrImportedResult");
    private final static QName _GetFileResponseGetFileResult_QNAME = new QName("http://www.gratex.com/PerConIk/AstRcs/IEntityService", "GetFileResult");
    private final static QName _SearchChangesetsResponseSearchChangesetsResult_QNAME = new QName("http://www.gratex.com/PerConIk/AstRcs/IEntityService", "SearchChangesetsResult");
    private final static QName _GetRcsServerResponseGetRcsServerResult_QNAME = new QName("http://www.gratex.com/PerConIk/AstRcs/IEntityService", "GetRcsServerResult");
    private final static QName _EnsureRcsServerResponseEnsureRcsServerResult_QNAME = new QName("http://www.gratex.com/PerConIk/AstRcs/IEntityService", "EnsureRcsServerResult");
    private final static QName _GetImportCapabilitiesResponseGetImportCapabilitiesResult_QNAME = new QName("http://www.gratex.com/PerConIk/AstRcs/IEntityService", "GetImportCapabilitiesResult");
    private final static QName _GetChangedFilesResponseGetChangedFilesResult_QNAME = new QName("http://www.gratex.com/PerConIk/AstRcs/IEntityService", "GetChangedFilesResult");
    private final static QName _SearchCodeEntitiesResponseSearchCodeEntitiesResult_QNAME = new QName("http://www.gratex.com/PerConIk/AstRcs/IEntityService", "SearchCodeEntitiesResult");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.gratex.perconik.services.ast.rcs.entity
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link com.gratex.perconik.services.ast.rcs.entity.GetImportCapabilitiesResponse }
     * 
     */
    public com.gratex.perconik.services.ast.rcs.entity.GetImportCapabilitiesResponse createGetImportCapabilitiesResponse() {
        return new com.gratex.perconik.services.ast.rcs.entity.GetImportCapabilitiesResponse();
    }

    /**
     * Create an instance of {@link com.gratex.perconik.services.ast.rcs.entity.SearchUsersResponse }
     * 
     */
    public com.gratex.perconik.services.ast.rcs.entity.SearchUsersResponse createSearchUsersResponse() {
        return new com.gratex.perconik.services.ast.rcs.entity.SearchUsersResponse();
    }

    /**
     * Create an instance of {@link GetUser }
     * 
     */
    public GetUser createGetUser() {
        return new GetUser();
    }

    /**
     * Create an instance of {@link com.gratex.perconik.services.ast.rcs.entity.SearchRcsServersResponse }
     * 
     */
    public com.gratex.perconik.services.ast.rcs.entity.SearchRcsServersResponse createSearchRcsServersResponse() {
        return new com.gratex.perconik.services.ast.rcs.entity.SearchRcsServersResponse();
    }

    /**
     * Create an instance of {@link com.gratex.perconik.services.ast.rcs.entity.AssociateChangesetWithBranchResponse }
     * 
     */
    public com.gratex.perconik.services.ast.rcs.entity.AssociateChangesetWithBranchResponse createAssociateChangesetWithBranchResponse() {
        return new com.gratex.perconik.services.ast.rcs.entity.AssociateChangesetWithBranchResponse();
    }

    /**
     * Create an instance of {@link QueueChangesetForImport }
     * 
     */
    public QueueChangesetForImport createQueueChangesetForImport() {
        return new QueueChangesetForImport();
    }

    /**
     * Create an instance of {@link com.gratex.perconik.services.ast.rcs.entity.GetBranchResponse }
     * 
     */
    public com.gratex.perconik.services.ast.rcs.entity.GetBranchResponse createGetBranchResponse() {
        return new com.gratex.perconik.services.ast.rcs.entity.GetBranchResponse();
    }

    /**
     * Create an instance of {@link com.gratex.perconik.services.ast.rcs.entity.GetCodeEntityFullContextResponse }
     * 
     */
    public com.gratex.perconik.services.ast.rcs.entity.GetCodeEntityFullContextResponse createGetCodeEntityFullContextResponse() {
        return new com.gratex.perconik.services.ast.rcs.entity.GetCodeEntityFullContextResponse();
    }

    /**
     * Create an instance of {@link com.gratex.perconik.services.ast.rcs.entity.SearchFoldersResponse }
     * 
     */
    public com.gratex.perconik.services.ast.rcs.entity.SearchFoldersResponse createSearchFoldersResponse() {
        return new com.gratex.perconik.services.ast.rcs.entity.SearchFoldersResponse();
    }

    /**
     * Create an instance of {@link com.gratex.perconik.services.ast.rcs.entity.IsChangesetQueuedOrImportedResponse }
     * 
     */
    public com.gratex.perconik.services.ast.rcs.entity.IsChangesetQueuedOrImportedResponse createIsChangesetQueuedOrImportedResponse() {
        return new com.gratex.perconik.services.ast.rcs.entity.IsChangesetQueuedOrImportedResponse();
    }

    /**
     * Create an instance of {@link com.gratex.perconik.services.ast.rcs.entity.SearchRcsProjectsResponse }
     * 
     */
    public com.gratex.perconik.services.ast.rcs.entity.SearchRcsProjectsResponse createSearchRcsProjectsResponse() {
        return new com.gratex.perconik.services.ast.rcs.entity.SearchRcsProjectsResponse();
    }

    /**
     * Create an instance of {@link GetBranchQueuedHead }
     * 
     */
    public GetBranchQueuedHead createGetBranchQueuedHead() {
        return new GetBranchQueuedHead();
    }

    /**
     * Create an instance of {@link GetRcsServer }
     * 
     */
    public GetRcsServer createGetRcsServer() {
        return new GetRcsServer();
    }

    /**
     * Create an instance of {@link SearchRcsServers }
     * 
     */
    public SearchRcsServers createSearchRcsServers() {
        return new SearchRcsServers();
    }

    /**
     * Create an instance of {@link GetCodeEntityChangesets }
     * 
     */
    public GetCodeEntityChangesets createGetCodeEntityChangesets() {
        return new GetCodeEntityChangesets();
    }

    /**
     * Create an instance of {@link SearchChangesets }
     * 
     */
    public SearchChangesets createSearchChangesets() {
        return new SearchChangesets();
    }

    /**
     * Create an instance of {@link com.gratex.perconik.services.ast.rcs.entity.GetFileChangesetsResponse }
     * 
     */
    public com.gratex.perconik.services.ast.rcs.entity.GetFileChangesetsResponse createGetFileChangesetsResponse() {
        return new com.gratex.perconik.services.ast.rcs.entity.GetFileChangesetsResponse();
    }

    /**
     * Create an instance of {@link com.gratex.perconik.services.ast.rcs.entity.GetChangedFilesResponse }
     * 
     */
    public com.gratex.perconik.services.ast.rcs.entity.GetChangedFilesResponse createGetChangedFilesResponse() {
        return new com.gratex.perconik.services.ast.rcs.entity.GetChangedFilesResponse();
    }

    /**
     * Create an instance of {@link GetChildCodeEntities }
     * 
     */
    public GetChildCodeEntities createGetChildCodeEntities() {
        return new GetChildCodeEntities();
    }

    /**
     * Create an instance of {@link com.gratex.perconik.services.ast.rcs.entity.SearchCodeEntitiesResponse }
     * 
     */
    public com.gratex.perconik.services.ast.rcs.entity.SearchCodeEntitiesResponse createSearchCodeEntitiesResponse() {
        return new com.gratex.perconik.services.ast.rcs.entity.SearchCodeEntitiesResponse();
    }

    /**
     * Create an instance of {@link GetFileChangesets }
     * 
     */
    public GetFileChangesets createGetFileChangesets() {
        return new GetFileChangesets();
    }

    /**
     * Create an instance of {@link com.gratex.perconik.services.ast.rcs.entity.GetBranchImportedHeadResponse }
     * 
     */
    public com.gratex.perconik.services.ast.rcs.entity.GetBranchImportedHeadResponse createGetBranchImportedHeadResponse() {
        return new com.gratex.perconik.services.ast.rcs.entity.GetBranchImportedHeadResponse();
    }

    /**
     * Create an instance of {@link GetCodeEntity }
     * 
     */
    public GetCodeEntity createGetCodeEntity() {
        return new GetCodeEntity();
    }

    /**
     * Create an instance of {@link GetImportCapabilities }
     * 
     */
    public GetImportCapabilities createGetImportCapabilities() {
        return new GetImportCapabilities();
    }

    /**
     * Create an instance of {@link com.gratex.perconik.services.ast.rcs.entity.GetFilesByGitIdentifiersResponse }
     * 
     */
    public com.gratex.perconik.services.ast.rcs.entity.GetFilesByGitIdentifiersResponse createGetFilesByGitIdentifiersResponse() {
        return new com.gratex.perconik.services.ast.rcs.entity.GetFilesByGitIdentifiersResponse();
    }

    /**
     * Create an instance of {@link SearchRcsProjects }
     * 
     */
    public SearchRcsProjects createSearchRcsProjects() {
        return new SearchRcsProjects();
    }

    /**
     * Create an instance of {@link com.gratex.perconik.services.ast.rcs.entity.GetChildCodeEntitiesResponse }
     * 
     */
    public com.gratex.perconik.services.ast.rcs.entity.GetChildCodeEntitiesResponse createGetChildCodeEntitiesResponse() {
        return new com.gratex.perconik.services.ast.rcs.entity.GetChildCodeEntitiesResponse();
    }

    /**
     * Create an instance of {@link com.gratex.perconik.services.ast.rcs.entity.EnsureRcsProjectResponse }
     * 
     */
    public com.gratex.perconik.services.ast.rcs.entity.EnsureRcsProjectResponse createEnsureRcsProjectResponse() {
        return new com.gratex.perconik.services.ast.rcs.entity.EnsureRcsProjectResponse();
    }

    /**
     * Create an instance of {@link EnsureBranches }
     * 
     */
    public EnsureBranches createEnsureBranches() {
        return new EnsureBranches();
    }

    /**
     * Create an instance of {@link GetFilesByGitIdentifiers }
     * 
     */
    public GetFilesByGitIdentifiers createGetFilesByGitIdentifiers() {
        return new GetFilesByGitIdentifiers();
    }

    /**
     * Create an instance of {@link GetChangedFiles }
     * 
     */
    public GetChangedFiles createGetChangedFiles() {
        return new GetChangedFiles();
    }

    /**
     * Create an instance of {@link com.gratex.perconik.services.ast.rcs.entity.GetCodeEntityContentResponse }
     * 
     */
    public com.gratex.perconik.services.ast.rcs.entity.GetCodeEntityContentResponse createGetCodeEntityContentResponse() {
        return new com.gratex.perconik.services.ast.rcs.entity.GetCodeEntityContentResponse();
    }

    /**
     * Create an instance of {@link com.gratex.perconik.services.ast.rcs.entity.QueueChangesetForImportResponse }
     * 
     */
    public com.gratex.perconik.services.ast.rcs.entity.QueueChangesetForImportResponse createQueueChangesetForImportResponse() {
        return new com.gratex.perconik.services.ast.rcs.entity.QueueChangesetForImportResponse();
    }

    /**
     * Create an instance of {@link com.gratex.perconik.services.ast.rcs.entity.GetChangesetRcsProjectResponse }
     * 
     */
    public com.gratex.perconik.services.ast.rcs.entity.GetChangesetRcsProjectResponse createGetChangesetRcsProjectResponse() {
        return new com.gratex.perconik.services.ast.rcs.entity.GetChangesetRcsProjectResponse();
    }

    /**
     * Create an instance of {@link GetFile }
     * 
     */
    public GetFile createGetFile() {
        return new GetFile();
    }

    /**
     * Create an instance of {@link EnsureRcsProject }
     * 
     */
    public EnsureRcsProject createEnsureRcsProject() {
        return new EnsureRcsProject();
    }

    /**
     * Create an instance of {@link com.gratex.perconik.services.ast.rcs.entity.SearchTypeCodeEntitiesLightResponse }
     * 
     */
    public com.gratex.perconik.services.ast.rcs.entity.SearchTypeCodeEntitiesLightResponse createSearchTypeCodeEntitiesLightResponse() {
        return new com.gratex.perconik.services.ast.rcs.entity.SearchTypeCodeEntitiesLightResponse();
    }

    /**
     * Create an instance of {@link com.gratex.perconik.services.ast.rcs.entity.EnsureBranchesResponse }
     * 
     */
    public com.gratex.perconik.services.ast.rcs.entity.EnsureBranchesResponse createEnsureBranchesResponse() {
        return new com.gratex.perconik.services.ast.rcs.entity.EnsureBranchesResponse();
    }

    /**
     * Create an instance of {@link GetCodeEntityFullContext }
     * 
     */
    public GetCodeEntityFullContext createGetCodeEntityFullContext() {
        return new GetCodeEntityFullContext();
    }

    /**
     * Create an instance of {@link GetRcsProject }
     * 
     */
    public GetRcsProject createGetRcsProject() {
        return new GetRcsProject();
    }

    /**
     * Create an instance of {@link SearchTypeCodeEntitiesLight }
     * 
     */
    public SearchTypeCodeEntitiesLight createSearchTypeCodeEntitiesLight() {
        return new SearchTypeCodeEntitiesLight();
    }

    /**
     * Create an instance of {@link GetFilesByTfsIdentifiers }
     * 
     */
    public GetFilesByTfsIdentifiers createGetFilesByTfsIdentifiers() {
        return new GetFilesByTfsIdentifiers();
    }

    /**
     * Create an instance of {@link AssociateChangesetWithBranch }
     * 
     */
    public AssociateChangesetWithBranch createAssociateChangesetWithBranch() {
        return new AssociateChangesetWithBranch();
    }

    /**
     * Create an instance of {@link IsChangesetQueuedOrImported }
     * 
     */
    public IsChangesetQueuedOrImported createIsChangesetQueuedOrImported() {
        return new IsChangesetQueuedOrImported();
    }

    /**
     * Create an instance of {@link com.gratex.perconik.services.ast.rcs.entity.GetRcsServerResponse }
     * 
     */
    public com.gratex.perconik.services.ast.rcs.entity.GetRcsServerResponse createGetRcsServerResponse() {
        return new com.gratex.perconik.services.ast.rcs.entity.GetRcsServerResponse();
    }

    /**
     * Create an instance of {@link com.gratex.perconik.services.ast.rcs.entity.GetFilesByTfsIdentifiersResponse }
     * 
     */
    public com.gratex.perconik.services.ast.rcs.entity.GetFilesByTfsIdentifiersResponse createGetFilesByTfsIdentifiersResponse() {
        return new com.gratex.perconik.services.ast.rcs.entity.GetFilesByTfsIdentifiersResponse();
    }

    /**
     * Create an instance of {@link SearchBranches }
     * 
     */
    public SearchBranches createSearchBranches() {
        return new SearchBranches();
    }

    /**
     * Create an instance of {@link SearchFolders }
     * 
     */
    public SearchFolders createSearchFolders() {
        return new SearchFolders();
    }

    /**
     * Create an instance of {@link GetChangesetRcsProject }
     * 
     */
    public GetChangesetRcsProject createGetChangesetRcsProject() {
        return new GetChangesetRcsProject();
    }

    /**
     * Create an instance of {@link com.gratex.perconik.services.ast.rcs.entity.GetCodeEntityResponse }
     * 
     */
    public com.gratex.perconik.services.ast.rcs.entity.GetCodeEntityResponse createGetCodeEntityResponse() {
        return new com.gratex.perconik.services.ast.rcs.entity.GetCodeEntityResponse();
    }

    /**
     * Create an instance of {@link com.gratex.perconik.services.ast.rcs.entity.GetFileContentResponse }
     * 
     */
    public com.gratex.perconik.services.ast.rcs.entity.GetFileContentResponse createGetFileContentResponse() {
        return new com.gratex.perconik.services.ast.rcs.entity.GetFileContentResponse();
    }

    /**
     * Create an instance of {@link SearchCodeEntities }
     * 
     */
    public SearchCodeEntities createSearchCodeEntities() {
        return new SearchCodeEntities();
    }

    /**
     * Create an instance of {@link GetCodeEntityContent }
     * 
     */
    public GetCodeEntityContent createGetCodeEntityContent() {
        return new GetCodeEntityContent();
    }

    /**
     * Create an instance of {@link com.gratex.perconik.services.ast.rcs.entity.GetUserResponse }
     * 
     */
    public com.gratex.perconik.services.ast.rcs.entity.GetUserResponse createGetUserResponse() {
        return new com.gratex.perconik.services.ast.rcs.entity.GetUserResponse();
    }

    /**
     * Create an instance of {@link com.gratex.perconik.services.ast.rcs.entity.EnsureRcsServerResponse }
     * 
     */
    public com.gratex.perconik.services.ast.rcs.entity.EnsureRcsServerResponse createEnsureRcsServerResponse() {
        return new com.gratex.perconik.services.ast.rcs.entity.EnsureRcsServerResponse();
    }

    /**
     * Create an instance of {@link EnsureRcsServer }
     * 
     */
    public EnsureRcsServer createEnsureRcsServer() {
        return new EnsureRcsServer();
    }

    /**
     * Create an instance of {@link com.gratex.perconik.services.ast.rcs.entity.GetFileResponse }
     * 
     */
    public com.gratex.perconik.services.ast.rcs.entity.GetFileResponse createGetFileResponse() {
        return new com.gratex.perconik.services.ast.rcs.entity.GetFileResponse();
    }

    /**
     * Create an instance of {@link SearchUsers }
     * 
     */
    public SearchUsers createSearchUsers() {
        return new SearchUsers();
    }

    /**
     * Create an instance of {@link GetBranch }
     * 
     */
    public GetBranch createGetBranch() {
        return new GetBranch();
    }

    /**
     * Create an instance of {@link com.gratex.perconik.services.ast.rcs.entity.GetBranchQueuedHeadResponse }
     * 
     */
    public com.gratex.perconik.services.ast.rcs.entity.GetBranchQueuedHeadResponse createGetBranchQueuedHeadResponse() {
        return new com.gratex.perconik.services.ast.rcs.entity.GetBranchQueuedHeadResponse();
    }

    /**
     * Create an instance of {@link SearchFiles }
     * 
     */
    public SearchFiles createSearchFiles() {
        return new SearchFiles();
    }

    /**
     * Create an instance of {@link GetBranchImportedHead }
     * 
     */
    public GetBranchImportedHead createGetBranchImportedHead() {
        return new GetBranchImportedHead();
    }

    /**
     * Create an instance of {@link com.gratex.perconik.services.ast.rcs.entity.GetRcsProjectResponse }
     * 
     */
    public com.gratex.perconik.services.ast.rcs.entity.GetRcsProjectResponse createGetRcsProjectResponse() {
        return new com.gratex.perconik.services.ast.rcs.entity.GetRcsProjectResponse();
    }

    /**
     * Create an instance of {@link com.gratex.perconik.services.ast.rcs.entity.SearchChangesetsResponse }
     * 
     */
    public com.gratex.perconik.services.ast.rcs.entity.SearchChangesetsResponse createSearchChangesetsResponse() {
        return new com.gratex.perconik.services.ast.rcs.entity.SearchChangesetsResponse();
    }

    /**
     * Create an instance of {@link com.gratex.perconik.services.ast.rcs.entity.SearchFilesResponse }
     * 
     */
    public com.gratex.perconik.services.ast.rcs.entity.SearchFilesResponse createSearchFilesResponse() {
        return new com.gratex.perconik.services.ast.rcs.entity.SearchFilesResponse();
    }

    /**
     * Create an instance of {@link GetChangeset }
     * 
     */
    public GetChangeset createGetChangeset() {
        return new GetChangeset();
    }

    /**
     * Create an instance of {@link com.gratex.perconik.services.ast.rcs.entity.GetChangesetResponse }
     * 
     */
    public com.gratex.perconik.services.ast.rcs.entity.GetChangesetResponse createGetChangesetResponse() {
        return new com.gratex.perconik.services.ast.rcs.entity.GetChangesetResponse();
    }

    /**
     * Create an instance of {@link com.gratex.perconik.services.ast.rcs.entity.GetCodeEntityChangesetsResponse }
     * 
     */
    public com.gratex.perconik.services.ast.rcs.entity.GetCodeEntityChangesetsResponse createGetCodeEntityChangesetsResponse() {
        return new com.gratex.perconik.services.ast.rcs.entity.GetCodeEntityChangesetsResponse();
    }

    /**
     * Create an instance of {@link com.gratex.perconik.services.ast.rcs.entity.SearchBranchesResponse }
     * 
     */
    public com.gratex.perconik.services.ast.rcs.entity.SearchBranchesResponse createSearchBranchesResponse() {
        return new com.gratex.perconik.services.ast.rcs.entity.SearchBranchesResponse();
    }

    /**
     * Create an instance of {@link GetFileContent }
     * 
     */
    public GetFileContent createGetFileContent() {
        return new GetFileContent();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link com.gratex.perconik.services.ast.rcs.GetCodeEntityResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.gratex.com/PerConIk/AstRcs/IEntityService", name = "GetCodeEntityResult", scope = com.gratex.perconik.services.ast.rcs.entity.GetCodeEntityResponse.class)
    public JAXBElement<com.gratex.perconik.services.ast.rcs.GetCodeEntityResponse> createGetCodeEntityResponseGetCodeEntityResult(com.gratex.perconik.services.ast.rcs.GetCodeEntityResponse value) {
        return new JAXBElement<com.gratex.perconik.services.ast.rcs.GetCodeEntityResponse>(_GetCodeEntityResponseGetCodeEntityResult_QNAME, com.gratex.perconik.services.ast.rcs.GetCodeEntityResponse.class, com.gratex.perconik.services.ast.rcs.entity.GetCodeEntityResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link com.gratex.perconik.services.ast.rcs.SearchTypeCodeEntitiesLightResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.gratex.com/PerConIk/AstRcs/IEntityService", name = "SearchTypeCodeEntitiesLightResult", scope = com.gratex.perconik.services.ast.rcs.entity.SearchTypeCodeEntitiesLightResponse.class)
    public JAXBElement<com.gratex.perconik.services.ast.rcs.SearchTypeCodeEntitiesLightResponse> createSearchTypeCodeEntitiesLightResponseSearchTypeCodeEntitiesLightResult(com.gratex.perconik.services.ast.rcs.SearchTypeCodeEntitiesLightResponse value) {
        return new JAXBElement<com.gratex.perconik.services.ast.rcs.SearchTypeCodeEntitiesLightResponse>(_SearchTypeCodeEntitiesLightResponseSearchTypeCodeEntitiesLightResult_QNAME, com.gratex.perconik.services.ast.rcs.SearchTypeCodeEntitiesLightResponse.class, com.gratex.perconik.services.ast.rcs.entity.SearchTypeCodeEntitiesLightResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetCodeEntityFullContextRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.gratex.com/PerConIk/AstRcs/IEntityService", name = "request", scope = GetCodeEntityFullContext.class)
    public JAXBElement<GetCodeEntityFullContextRequest> createGetCodeEntityFullContextRequest(GetCodeEntityFullContextRequest value) {
        return new JAXBElement<GetCodeEntityFullContextRequest>(_GetCodeEntityFullContextRequest_QNAME, GetCodeEntityFullContextRequest.class, GetCodeEntityFullContext.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link com.gratex.perconik.services.ast.rcs.GetChangesetRcsProjectResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.gratex.com/PerConIk/AstRcs/IEntityService", name = "GetChangesetRcsProjectResult", scope = com.gratex.perconik.services.ast.rcs.entity.GetChangesetRcsProjectResponse.class)
    public JAXBElement<com.gratex.perconik.services.ast.rcs.GetChangesetRcsProjectResponse> createGetChangesetRcsProjectResponseGetChangesetRcsProjectResult(com.gratex.perconik.services.ast.rcs.GetChangesetRcsProjectResponse value) {
        return new JAXBElement<com.gratex.perconik.services.ast.rcs.GetChangesetRcsProjectResponse>(_GetChangesetRcsProjectResponseGetChangesetRcsProjectResult_QNAME, com.gratex.perconik.services.ast.rcs.GetChangesetRcsProjectResponse.class, com.gratex.perconik.services.ast.rcs.entity.GetChangesetRcsProjectResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EnsureRcsProjectRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.gratex.com/PerConIk/AstRcs/IEntityService", name = "request", scope = EnsureRcsProject.class)
    public JAXBElement<EnsureRcsProjectRequest> createEnsureRcsProjectRequest(EnsureRcsProjectRequest value) {
        return new JAXBElement<EnsureRcsProjectRequest>(_GetCodeEntityFullContextRequest_QNAME, EnsureRcsProjectRequest.class, EnsureRcsProject.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link com.gratex.perconik.services.ast.rcs.EnsureBranchesResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.gratex.com/PerConIk/AstRcs/IEntityService", name = "EnsureBranchesResult", scope = com.gratex.perconik.services.ast.rcs.entity.EnsureBranchesResponse.class)
    public JAXBElement<com.gratex.perconik.services.ast.rcs.EnsureBranchesResponse> createEnsureBranchesResponseEnsureBranchesResult(com.gratex.perconik.services.ast.rcs.EnsureBranchesResponse value) {
        return new JAXBElement<com.gratex.perconik.services.ast.rcs.EnsureBranchesResponse>(_EnsureBranchesResponseEnsureBranchesResult_QNAME, com.gratex.perconik.services.ast.rcs.EnsureBranchesResponse.class, com.gratex.perconik.services.ast.rcs.entity.EnsureBranchesResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link com.gratex.perconik.services.ast.rcs.SearchRcsServersResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.gratex.com/PerConIk/AstRcs/IEntityService", name = "SearchRcsServersResult", scope = com.gratex.perconik.services.ast.rcs.entity.SearchRcsServersResponse.class)
    public JAXBElement<com.gratex.perconik.services.ast.rcs.SearchRcsServersResponse> createSearchRcsServersResponseSearchRcsServersResult(com.gratex.perconik.services.ast.rcs.SearchRcsServersResponse value) {
        return new JAXBElement<com.gratex.perconik.services.ast.rcs.SearchRcsServersResponse>(_SearchRcsServersResponseSearchRcsServersResult_QNAME, com.gratex.perconik.services.ast.rcs.SearchRcsServersResponse.class, com.gratex.perconik.services.ast.rcs.entity.SearchRcsServersResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link com.gratex.perconik.services.ast.rcs.GetChildCodeEntitiesResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.gratex.com/PerConIk/AstRcs/IEntityService", name = "GetChildCodeEntitiesResult", scope = com.gratex.perconik.services.ast.rcs.entity.GetChildCodeEntitiesResponse.class)
    public JAXBElement<com.gratex.perconik.services.ast.rcs.GetChildCodeEntitiesResponse> createGetChildCodeEntitiesResponseGetChildCodeEntitiesResult(com.gratex.perconik.services.ast.rcs.GetChildCodeEntitiesResponse value) {
        return new JAXBElement<com.gratex.perconik.services.ast.rcs.GetChildCodeEntitiesResponse>(_GetChildCodeEntitiesResponseGetChildCodeEntitiesResult_QNAME, com.gratex.perconik.services.ast.rcs.GetChildCodeEntitiesResponse.class, com.gratex.perconik.services.ast.rcs.entity.GetChildCodeEntitiesResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link com.gratex.perconik.services.ast.rcs.GetRcsProjectResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.gratex.com/PerConIk/AstRcs/IEntityService", name = "GetRcsProjectResult", scope = com.gratex.perconik.services.ast.rcs.entity.GetRcsProjectResponse.class)
    public JAXBElement<com.gratex.perconik.services.ast.rcs.GetRcsProjectResponse> createGetRcsProjectResponseGetRcsProjectResult(com.gratex.perconik.services.ast.rcs.GetRcsProjectResponse value) {
        return new JAXBElement<com.gratex.perconik.services.ast.rcs.GetRcsProjectResponse>(_GetRcsProjectResponseGetRcsProjectResult_QNAME, com.gratex.perconik.services.ast.rcs.GetRcsProjectResponse.class, com.gratex.perconik.services.ast.rcs.entity.GetRcsProjectResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetCodeEntityChangesetsRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.gratex.com/PerConIk/AstRcs/IEntityService", name = "request", scope = GetCodeEntityChangesets.class)
    public JAXBElement<GetCodeEntityChangesetsRequest> createGetCodeEntityChangesetsRequest(GetCodeEntityChangesetsRequest value) {
        return new JAXBElement<GetCodeEntityChangesetsRequest>(_GetCodeEntityFullContextRequest_QNAME, GetCodeEntityChangesetsRequest.class, GetCodeEntityChangesets.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetUserRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.gratex.com/PerConIk/AstRcs/IEntityService", name = "request", scope = GetUser.class)
    public JAXBElement<GetUserRequest> createGetUserRequest(GetUserRequest value) {
        return new JAXBElement<GetUserRequest>(_GetCodeEntityFullContextRequest_QNAME, GetUserRequest.class, GetUser.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetRcsServerRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.gratex.com/PerConIk/AstRcs/IEntityService", name = "request", scope = GetRcsServer.class)
    public JAXBElement<GetRcsServerRequest> createGetRcsServerRequest(GetRcsServerRequest value) {
        return new JAXBElement<GetRcsServerRequest>(_GetCodeEntityFullContextRequest_QNAME, GetRcsServerRequest.class, GetRcsServer.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetChangesetRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.gratex.com/PerConIk/AstRcs/IEntityService", name = "request", scope = GetChangeset.class)
    public JAXBElement<GetChangesetRequest> createGetChangesetRequest(GetChangesetRequest value) {
        return new JAXBElement<GetChangesetRequest>(_GetCodeEntityFullContextRequest_QNAME, GetChangesetRequest.class, GetChangeset.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link com.gratex.perconik.services.ast.rcs.GetBranchImportedHeadResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.gratex.com/PerConIk/AstRcs/IEntityService", name = "GetBranchImportedHeadResult", scope = com.gratex.perconik.services.ast.rcs.entity.GetBranchImportedHeadResponse.class)
    public JAXBElement<com.gratex.perconik.services.ast.rcs.GetBranchImportedHeadResponse> createGetBranchImportedHeadResponseGetBranchImportedHeadResult(com.gratex.perconik.services.ast.rcs.GetBranchImportedHeadResponse value) {
        return new JAXBElement<com.gratex.perconik.services.ast.rcs.GetBranchImportedHeadResponse>(_GetBranchImportedHeadResponseGetBranchImportedHeadResult_QNAME, com.gratex.perconik.services.ast.rcs.GetBranchImportedHeadResponse.class, com.gratex.perconik.services.ast.rcs.entity.GetBranchImportedHeadResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link com.gratex.perconik.services.ast.rcs.QueueChangesetForImportResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.gratex.com/PerConIk/AstRcs/IEntityService", name = "QueueChangesetForImportResult", scope = com.gratex.perconik.services.ast.rcs.entity.QueueChangesetForImportResponse.class)
    public JAXBElement<com.gratex.perconik.services.ast.rcs.QueueChangesetForImportResponse> createQueueChangesetForImportResponseQueueChangesetForImportResult(com.gratex.perconik.services.ast.rcs.QueueChangesetForImportResponse value) {
        return new JAXBElement<com.gratex.perconik.services.ast.rcs.QueueChangesetForImportResponse>(_QueueChangesetForImportResponseQueueChangesetForImportResult_QNAME, com.gratex.perconik.services.ast.rcs.QueueChangesetForImportResponse.class, com.gratex.perconik.services.ast.rcs.entity.QueueChangesetForImportResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link com.gratex.perconik.services.ast.rcs.SearchUsersResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.gratex.com/PerConIk/AstRcs/IEntityService", name = "SearchUsersResult", scope = com.gratex.perconik.services.ast.rcs.entity.SearchUsersResponse.class)
    public JAXBElement<com.gratex.perconik.services.ast.rcs.SearchUsersResponse> createSearchUsersResponseSearchUsersResult(com.gratex.perconik.services.ast.rcs.SearchUsersResponse value) {
        return new JAXBElement<com.gratex.perconik.services.ast.rcs.SearchUsersResponse>(_SearchUsersResponseSearchUsersResult_QNAME, com.gratex.perconik.services.ast.rcs.SearchUsersResponse.class, com.gratex.perconik.services.ast.rcs.entity.SearchUsersResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SearchUsersRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.gratex.com/PerConIk/AstRcs/IEntityService", name = "request", scope = SearchUsers.class)
    public JAXBElement<SearchUsersRequest> createSearchUsersRequest(SearchUsersRequest value) {
        return new JAXBElement<SearchUsersRequest>(_GetCodeEntityFullContextRequest_QNAME, SearchUsersRequest.class, SearchUsers.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetCodeEntityContentRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.gratex.com/PerConIk/AstRcs/IEntityService", name = "request", scope = GetCodeEntityContent.class)
    public JAXBElement<GetCodeEntityContentRequest> createGetCodeEntityContentRequest(GetCodeEntityContentRequest value) {
        return new JAXBElement<GetCodeEntityContentRequest>(_GetCodeEntityFullContextRequest_QNAME, GetCodeEntityContentRequest.class, GetCodeEntityContent.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetBranchRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.gratex.com/PerConIk/AstRcs/IEntityService", name = "request", scope = GetBranch.class)
    public JAXBElement<GetBranchRequest> createGetBranchRequest(GetBranchRequest value) {
        return new JAXBElement<GetBranchRequest>(_GetCodeEntityFullContextRequest_QNAME, GetBranchRequest.class, GetBranch.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SearchFoldersRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.gratex.com/PerConIk/AstRcs/IEntityService", name = "request", scope = SearchFolders.class)
    public JAXBElement<SearchFoldersRequest> createSearchFoldersRequest(SearchFoldersRequest value) {
        return new JAXBElement<SearchFoldersRequest>(_GetCodeEntityFullContextRequest_QNAME, SearchFoldersRequest.class, SearchFolders.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetFilesByTfsIdentifiersRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.gratex.com/PerConIk/AstRcs/IEntityService", name = "request", scope = GetFilesByTfsIdentifiers.class)
    public JAXBElement<GetFilesByTfsIdentifiersRequest> createGetFilesByTfsIdentifiersRequest(GetFilesByTfsIdentifiersRequest value) {
        return new JAXBElement<GetFilesByTfsIdentifiersRequest>(_GetCodeEntityFullContextRequest_QNAME, GetFilesByTfsIdentifiersRequest.class, GetFilesByTfsIdentifiers.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetFileRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.gratex.com/PerConIk/AstRcs/IEntityService", name = "request", scope = GetFile.class)
    public JAXBElement<GetFileRequest> createGetFileRequest(GetFileRequest value) {
        return new JAXBElement<GetFileRequest>(_GetCodeEntityFullContextRequest_QNAME, GetFileRequest.class, GetFile.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetFileChangesetsRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.gratex.com/PerConIk/AstRcs/IEntityService", name = "request", scope = GetFileChangesets.class)
    public JAXBElement<GetFileChangesetsRequest> createGetFileChangesetsRequest(GetFileChangesetsRequest value) {
        return new JAXBElement<GetFileChangesetsRequest>(_GetCodeEntityFullContextRequest_QNAME, GetFileChangesetsRequest.class, GetFileChangesets.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetChangesetRcsProjectRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.gratex.com/PerConIk/AstRcs/IEntityService", name = "request", scope = GetChangesetRcsProject.class)
    public JAXBElement<GetChangesetRcsProjectRequest> createGetChangesetRcsProjectRequest(GetChangesetRcsProjectRequest value) {
        return new JAXBElement<GetChangesetRcsProjectRequest>(_GetCodeEntityFullContextRequest_QNAME, GetChangesetRcsProjectRequest.class, GetChangesetRcsProject.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link com.gratex.perconik.services.ast.rcs.GetChangesetResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.gratex.com/PerConIk/AstRcs/IEntityService", name = "GetChangesetResult", scope = com.gratex.perconik.services.ast.rcs.entity.GetChangesetResponse.class)
    public JAXBElement<com.gratex.perconik.services.ast.rcs.GetChangesetResponse> createGetChangesetResponseGetChangesetResult(com.gratex.perconik.services.ast.rcs.GetChangesetResponse value) {
        return new JAXBElement<com.gratex.perconik.services.ast.rcs.GetChangesetResponse>(_GetChangesetResponseGetChangesetResult_QNAME, com.gratex.perconik.services.ast.rcs.GetChangesetResponse.class, com.gratex.perconik.services.ast.rcs.entity.GetChangesetResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link QueueChangesetForImportRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.gratex.com/PerConIk/AstRcs/IEntityService", name = "request", scope = QueueChangesetForImport.class)
    public JAXBElement<QueueChangesetForImportRequest> createQueueChangesetForImportRequest(QueueChangesetForImportRequest value) {
        return new JAXBElement<QueueChangesetForImportRequest>(_GetCodeEntityFullContextRequest_QNAME, QueueChangesetForImportRequest.class, QueueChangesetForImport.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetChildCodeEntitiesRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.gratex.com/PerConIk/AstRcs/IEntityService", name = "request", scope = GetChildCodeEntities.class)
    public JAXBElement<GetChildCodeEntitiesRequest> createGetChildCodeEntitiesRequest(GetChildCodeEntitiesRequest value) {
        return new JAXBElement<GetChildCodeEntitiesRequest>(_GetCodeEntityFullContextRequest_QNAME, GetChildCodeEntitiesRequest.class, GetChildCodeEntities.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetFilesByGitIdentifiersRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.gratex.com/PerConIk/AstRcs/IEntityService", name = "request", scope = GetFilesByGitIdentifiers.class)
    public JAXBElement<GetFilesByGitIdentifiersRequest> createGetFilesByGitIdentifiersRequest(GetFilesByGitIdentifiersRequest value) {
        return new JAXBElement<GetFilesByGitIdentifiersRequest>(_GetCodeEntityFullContextRequest_QNAME, GetFilesByGitIdentifiersRequest.class, GetFilesByGitIdentifiers.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link com.gratex.perconik.services.ast.rcs.GetFileChangesetsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.gratex.com/PerConIk/AstRcs/IEntityService", name = "GetFileChangesetsResult", scope = com.gratex.perconik.services.ast.rcs.entity.GetFileChangesetsResponse.class)
    public JAXBElement<com.gratex.perconik.services.ast.rcs.GetFileChangesetsResponse> createGetFileChangesetsResponseGetFileChangesetsResult(com.gratex.perconik.services.ast.rcs.GetFileChangesetsResponse value) {
        return new JAXBElement<com.gratex.perconik.services.ast.rcs.GetFileChangesetsResponse>(_GetFileChangesetsResponseGetFileChangesetsResult_QNAME, com.gratex.perconik.services.ast.rcs.GetFileChangesetsResponse.class, com.gratex.perconik.services.ast.rcs.entity.GetFileChangesetsResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SearchFilesRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.gratex.com/PerConIk/AstRcs/IEntityService", name = "request", scope = SearchFiles.class)
    public JAXBElement<SearchFilesRequest> createSearchFilesRequest(SearchFilesRequest value) {
        return new JAXBElement<SearchFilesRequest>(_GetCodeEntityFullContextRequest_QNAME, SearchFilesRequest.class, SearchFiles.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link com.gratex.perconik.services.ast.rcs.AssociateChangesetWithBranchResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.gratex.com/PerConIk/AstRcs/IEntityService", name = "AssociateChangesetWithBranchResult", scope = com.gratex.perconik.services.ast.rcs.entity.AssociateChangesetWithBranchResponse.class)
    public JAXBElement<com.gratex.perconik.services.ast.rcs.AssociateChangesetWithBranchResponse> createAssociateChangesetWithBranchResponseAssociateChangesetWithBranchResult(com.gratex.perconik.services.ast.rcs.AssociateChangesetWithBranchResponse value) {
        return new JAXBElement<com.gratex.perconik.services.ast.rcs.AssociateChangesetWithBranchResponse>(_AssociateChangesetWithBranchResponseAssociateChangesetWithBranchResult_QNAME, com.gratex.perconik.services.ast.rcs.AssociateChangesetWithBranchResponse.class, com.gratex.perconik.services.ast.rcs.entity.AssociateChangesetWithBranchResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link com.gratex.perconik.services.ast.rcs.GetUserResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.gratex.com/PerConIk/AstRcs/IEntityService", name = "GetUserResult", scope = com.gratex.perconik.services.ast.rcs.entity.GetUserResponse.class)
    public JAXBElement<com.gratex.perconik.services.ast.rcs.GetUserResponse> createGetUserResponseGetUserResult(com.gratex.perconik.services.ast.rcs.GetUserResponse value) {
        return new JAXBElement<com.gratex.perconik.services.ast.rcs.GetUserResponse>(_GetUserResponseGetUserResult_QNAME, com.gratex.perconik.services.ast.rcs.GetUserResponse.class, com.gratex.perconik.services.ast.rcs.entity.GetUserResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link com.gratex.perconik.services.ast.rcs.GetFileContentResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.gratex.com/PerConIk/AstRcs/IEntityService", name = "GetFileContentResult", scope = com.gratex.perconik.services.ast.rcs.entity.GetFileContentResponse.class)
    public JAXBElement<com.gratex.perconik.services.ast.rcs.GetFileContentResponse> createGetFileContentResponseGetFileContentResult(com.gratex.perconik.services.ast.rcs.GetFileContentResponse value) {
        return new JAXBElement<com.gratex.perconik.services.ast.rcs.GetFileContentResponse>(_GetFileContentResponseGetFileContentResult_QNAME, com.gratex.perconik.services.ast.rcs.GetFileContentResponse.class, com.gratex.perconik.services.ast.rcs.entity.GetFileContentResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetCodeEntityRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.gratex.com/PerConIk/AstRcs/IEntityService", name = "request", scope = GetCodeEntity.class)
    public JAXBElement<GetCodeEntityRequest> createGetCodeEntityRequest(GetCodeEntityRequest value) {
        return new JAXBElement<GetCodeEntityRequest>(_GetCodeEntityFullContextRequest_QNAME, GetCodeEntityRequest.class, GetCodeEntity.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EnsureBranchesRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.gratex.com/PerConIk/AstRcs/IEntityService", name = "request", scope = EnsureBranches.class)
    public JAXBElement<EnsureBranchesRequest> createEnsureBranchesRequest(EnsureBranchesRequest value) {
        return new JAXBElement<EnsureBranchesRequest>(_GetCodeEntityFullContextRequest_QNAME, EnsureBranchesRequest.class, EnsureBranches.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link com.gratex.perconik.services.ast.rcs.GetFilesByGitIdentifiersResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.gratex.com/PerConIk/AstRcs/IEntityService", name = "GetFilesByGitIdentifiersResult", scope = com.gratex.perconik.services.ast.rcs.entity.GetFilesByGitIdentifiersResponse.class)
    public JAXBElement<com.gratex.perconik.services.ast.rcs.GetFilesByGitIdentifiersResponse> createGetFilesByGitIdentifiersResponseGetFilesByGitIdentifiersResult(com.gratex.perconik.services.ast.rcs.GetFilesByGitIdentifiersResponse value) {
        return new JAXBElement<com.gratex.perconik.services.ast.rcs.GetFilesByGitIdentifiersResponse>(_GetFilesByGitIdentifiersResponseGetFilesByGitIdentifiersResult_QNAME, com.gratex.perconik.services.ast.rcs.GetFilesByGitIdentifiersResponse.class, com.gratex.perconik.services.ast.rcs.entity.GetFilesByGitIdentifiersResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link com.gratex.perconik.services.ast.rcs.EnsureRcsProjectResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.gratex.com/PerConIk/AstRcs/IEntityService", name = "EnsureRcsProjectResult", scope = com.gratex.perconik.services.ast.rcs.entity.EnsureRcsProjectResponse.class)
    public JAXBElement<com.gratex.perconik.services.ast.rcs.EnsureRcsProjectResponse> createEnsureRcsProjectResponseEnsureRcsProjectResult(com.gratex.perconik.services.ast.rcs.EnsureRcsProjectResponse value) {
        return new JAXBElement<com.gratex.perconik.services.ast.rcs.EnsureRcsProjectResponse>(_EnsureRcsProjectResponseEnsureRcsProjectResult_QNAME, com.gratex.perconik.services.ast.rcs.EnsureRcsProjectResponse.class, com.gratex.perconik.services.ast.rcs.entity.EnsureRcsProjectResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link com.gratex.perconik.services.ast.rcs.GetBranchQueuedHeadResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.gratex.com/PerConIk/AstRcs/IEntityService", name = "GetBranchQueuedHeadResult", scope = com.gratex.perconik.services.ast.rcs.entity.GetBranchQueuedHeadResponse.class)
    public JAXBElement<com.gratex.perconik.services.ast.rcs.GetBranchQueuedHeadResponse> createGetBranchQueuedHeadResponseGetBranchQueuedHeadResult(com.gratex.perconik.services.ast.rcs.GetBranchQueuedHeadResponse value) {
        return new JAXBElement<com.gratex.perconik.services.ast.rcs.GetBranchQueuedHeadResponse>(_GetBranchQueuedHeadResponseGetBranchQueuedHeadResult_QNAME, com.gratex.perconik.services.ast.rcs.GetBranchQueuedHeadResponse.class, com.gratex.perconik.services.ast.rcs.entity.GetBranchQueuedHeadResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SearchRcsServersRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.gratex.com/PerConIk/AstRcs/IEntityService", name = "request", scope = SearchRcsServers.class)
    public JAXBElement<SearchRcsServersRequest> createSearchRcsServersRequest(SearchRcsServersRequest value) {
        return new JAXBElement<SearchRcsServersRequest>(_GetCodeEntityFullContextRequest_QNAME, SearchRcsServersRequest.class, SearchRcsServers.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link com.gratex.perconik.services.ast.rcs.SearchRcsProjectsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.gratex.com/PerConIk/AstRcs/IEntityService", name = "SearchRcsProjectsResult", scope = com.gratex.perconik.services.ast.rcs.entity.SearchRcsProjectsResponse.class)
    public JAXBElement<com.gratex.perconik.services.ast.rcs.SearchRcsProjectsResponse> createSearchRcsProjectsResponseSearchRcsProjectsResult(com.gratex.perconik.services.ast.rcs.SearchRcsProjectsResponse value) {
        return new JAXBElement<com.gratex.perconik.services.ast.rcs.SearchRcsProjectsResponse>(_SearchRcsProjectsResponseSearchRcsProjectsResult_QNAME, com.gratex.perconik.services.ast.rcs.SearchRcsProjectsResponse.class, com.gratex.perconik.services.ast.rcs.entity.SearchRcsProjectsResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link com.gratex.perconik.services.ast.rcs.SearchBranchesResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.gratex.com/PerConIk/AstRcs/IEntityService", name = "SearchBranchesResult", scope = com.gratex.perconik.services.ast.rcs.entity.SearchBranchesResponse.class)
    public JAXBElement<com.gratex.perconik.services.ast.rcs.SearchBranchesResponse> createSearchBranchesResponseSearchBranchesResult(com.gratex.perconik.services.ast.rcs.SearchBranchesResponse value) {
        return new JAXBElement<com.gratex.perconik.services.ast.rcs.SearchBranchesResponse>(_SearchBranchesResponseSearchBranchesResult_QNAME, com.gratex.perconik.services.ast.rcs.SearchBranchesResponse.class, com.gratex.perconik.services.ast.rcs.entity.SearchBranchesResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link com.gratex.perconik.services.ast.rcs.GetBranchResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.gratex.com/PerConIk/AstRcs/IEntityService", name = "GetBranchResult", scope = com.gratex.perconik.services.ast.rcs.entity.GetBranchResponse.class)
    public JAXBElement<com.gratex.perconik.services.ast.rcs.GetBranchResponse> createGetBranchResponseGetBranchResult(com.gratex.perconik.services.ast.rcs.GetBranchResponse value) {
        return new JAXBElement<com.gratex.perconik.services.ast.rcs.GetBranchResponse>(_GetBranchResponseGetBranchResult_QNAME, com.gratex.perconik.services.ast.rcs.GetBranchResponse.class, com.gratex.perconik.services.ast.rcs.entity.GetBranchResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetRcsProjectRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.gratex.com/PerConIk/AstRcs/IEntityService", name = "request", scope = GetRcsProject.class)
    public JAXBElement<GetRcsProjectRequest> createGetRcsProjectRequest(GetRcsProjectRequest value) {
        return new JAXBElement<GetRcsProjectRequest>(_GetCodeEntityFullContextRequest_QNAME, GetRcsProjectRequest.class, GetRcsProject.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SearchBranchesRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.gratex.com/PerConIk/AstRcs/IEntityService", name = "request", scope = SearchBranches.class)
    public JAXBElement<SearchBranchesRequest> createSearchBranchesRequest(SearchBranchesRequest value) {
        return new JAXBElement<SearchBranchesRequest>(_GetCodeEntityFullContextRequest_QNAME, SearchBranchesRequest.class, SearchBranches.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EnsureRcsServerRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.gratex.com/PerConIk/AstRcs/IEntityService", name = "request", scope = EnsureRcsServer.class)
    public JAXBElement<EnsureRcsServerRequest> createEnsureRcsServerRequest(EnsureRcsServerRequest value) {
        return new JAXBElement<EnsureRcsServerRequest>(_GetCodeEntityFullContextRequest_QNAME, EnsureRcsServerRequest.class, EnsureRcsServer.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AssociateChangesetWithBranchRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.gratex.com/PerConIk/AstRcs/IEntityService", name = "request", scope = AssociateChangesetWithBranch.class)
    public JAXBElement<AssociateChangesetWithBranchRequest> createAssociateChangesetWithBranchRequest(AssociateChangesetWithBranchRequest value) {
        return new JAXBElement<AssociateChangesetWithBranchRequest>(_GetCodeEntityFullContextRequest_QNAME, AssociateChangesetWithBranchRequest.class, AssociateChangesetWithBranch.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link com.gratex.perconik.services.ast.rcs.SearchFoldersResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.gratex.com/PerConIk/AstRcs/IEntityService", name = "SearchFoldersResult", scope = com.gratex.perconik.services.ast.rcs.entity.SearchFoldersResponse.class)
    public JAXBElement<com.gratex.perconik.services.ast.rcs.SearchFoldersResponse> createSearchFoldersResponseSearchFoldersResult(com.gratex.perconik.services.ast.rcs.SearchFoldersResponse value) {
        return new JAXBElement<com.gratex.perconik.services.ast.rcs.SearchFoldersResponse>(_SearchFoldersResponseSearchFoldersResult_QNAME, com.gratex.perconik.services.ast.rcs.SearchFoldersResponse.class, com.gratex.perconik.services.ast.rcs.entity.SearchFoldersResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link com.gratex.perconik.services.ast.rcs.GetCodeEntityChangesetsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.gratex.com/PerConIk/AstRcs/IEntityService", name = "GetCodeEntityChangesetsResult", scope = com.gratex.perconik.services.ast.rcs.entity.GetCodeEntityChangesetsResponse.class)
    public JAXBElement<com.gratex.perconik.services.ast.rcs.GetCodeEntityChangesetsResponse> createGetCodeEntityChangesetsResponseGetCodeEntityChangesetsResult(com.gratex.perconik.services.ast.rcs.GetCodeEntityChangesetsResponse value) {
        return new JAXBElement<com.gratex.perconik.services.ast.rcs.GetCodeEntityChangesetsResponse>(_GetCodeEntityChangesetsResponseGetCodeEntityChangesetsResult_QNAME, com.gratex.perconik.services.ast.rcs.GetCodeEntityChangesetsResponse.class, com.gratex.perconik.services.ast.rcs.entity.GetCodeEntityChangesetsResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetBranchImportedHeadRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.gratex.com/PerConIk/AstRcs/IEntityService", name = "request", scope = GetBranchImportedHead.class)
    public JAXBElement<GetBranchImportedHeadRequest> createGetBranchImportedHeadRequest(GetBranchImportedHeadRequest value) {
        return new JAXBElement<GetBranchImportedHeadRequest>(_GetCodeEntityFullContextRequest_QNAME, GetBranchImportedHeadRequest.class, GetBranchImportedHead.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link com.gratex.perconik.services.ast.rcs.SearchFilesResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.gratex.com/PerConIk/AstRcs/IEntityService", name = "SearchFilesResult", scope = com.gratex.perconik.services.ast.rcs.entity.SearchFilesResponse.class)
    public JAXBElement<com.gratex.perconik.services.ast.rcs.SearchFilesResponse> createSearchFilesResponseSearchFilesResult(com.gratex.perconik.services.ast.rcs.SearchFilesResponse value) {
        return new JAXBElement<com.gratex.perconik.services.ast.rcs.SearchFilesResponse>(_SearchFilesResponseSearchFilesResult_QNAME, com.gratex.perconik.services.ast.rcs.SearchFilesResponse.class, com.gratex.perconik.services.ast.rcs.entity.SearchFilesResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link com.gratex.perconik.services.ast.rcs.GetCodeEntityContentResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.gratex.com/PerConIk/AstRcs/IEntityService", name = "GetCodeEntityContentResult", scope = com.gratex.perconik.services.ast.rcs.entity.GetCodeEntityContentResponse.class)
    public JAXBElement<com.gratex.perconik.services.ast.rcs.GetCodeEntityContentResponse> createGetCodeEntityContentResponseGetCodeEntityContentResult(com.gratex.perconik.services.ast.rcs.GetCodeEntityContentResponse value) {
        return new JAXBElement<com.gratex.perconik.services.ast.rcs.GetCodeEntityContentResponse>(_GetCodeEntityContentResponseGetCodeEntityContentResult_QNAME, com.gratex.perconik.services.ast.rcs.GetCodeEntityContentResponse.class, com.gratex.perconik.services.ast.rcs.entity.GetCodeEntityContentResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SearchRcsProjectsRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.gratex.com/PerConIk/AstRcs/IEntityService", name = "request", scope = SearchRcsProjects.class)
    public JAXBElement<SearchRcsProjectsRequest> createSearchRcsProjectsRequest(SearchRcsProjectsRequest value) {
        return new JAXBElement<SearchRcsProjectsRequest>(_GetCodeEntityFullContextRequest_QNAME, SearchRcsProjectsRequest.class, SearchRcsProjects.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetBranchQueuedHeadRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.gratex.com/PerConIk/AstRcs/IEntityService", name = "request", scope = GetBranchQueuedHead.class)
    public JAXBElement<GetBranchQueuedHeadRequest> createGetBranchQueuedHeadRequest(GetBranchQueuedHeadRequest value) {
        return new JAXBElement<GetBranchQueuedHeadRequest>(_GetCodeEntityFullContextRequest_QNAME, GetBranchQueuedHeadRequest.class, GetBranchQueuedHead.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link com.gratex.perconik.services.ast.rcs.GetFilesByTfsIdentifiersResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.gratex.com/PerConIk/AstRcs/IEntityService", name = "GetFilesByTfsIdentifiersResult", scope = com.gratex.perconik.services.ast.rcs.entity.GetFilesByTfsIdentifiersResponse.class)
    public JAXBElement<com.gratex.perconik.services.ast.rcs.GetFilesByTfsIdentifiersResponse> createGetFilesByTfsIdentifiersResponseGetFilesByTfsIdentifiersResult(com.gratex.perconik.services.ast.rcs.GetFilesByTfsIdentifiersResponse value) {
        return new JAXBElement<com.gratex.perconik.services.ast.rcs.GetFilesByTfsIdentifiersResponse>(_GetFilesByTfsIdentifiersResponseGetFilesByTfsIdentifiersResult_QNAME, com.gratex.perconik.services.ast.rcs.GetFilesByTfsIdentifiersResponse.class, com.gratex.perconik.services.ast.rcs.entity.GetFilesByTfsIdentifiersResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link com.gratex.perconik.services.ast.rcs.GetCodeEntityFullContextResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.gratex.com/PerConIk/AstRcs/IEntityService", name = "GetCodeEntityFullContextResult", scope = com.gratex.perconik.services.ast.rcs.entity.GetCodeEntityFullContextResponse.class)
    public JAXBElement<com.gratex.perconik.services.ast.rcs.GetCodeEntityFullContextResponse> createGetCodeEntityFullContextResponseGetCodeEntityFullContextResult(com.gratex.perconik.services.ast.rcs.GetCodeEntityFullContextResponse value) {
        return new JAXBElement<com.gratex.perconik.services.ast.rcs.GetCodeEntityFullContextResponse>(_GetCodeEntityFullContextResponseGetCodeEntityFullContextResult_QNAME, com.gratex.perconik.services.ast.rcs.GetCodeEntityFullContextResponse.class, com.gratex.perconik.services.ast.rcs.entity.GetCodeEntityFullContextResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetFileContentRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.gratex.com/PerConIk/AstRcs/IEntityService", name = "request", scope = GetFileContent.class)
    public JAXBElement<GetFileContentRequest> createGetFileContentRequest(GetFileContentRequest value) {
        return new JAXBElement<GetFileContentRequest>(_GetCodeEntityFullContextRequest_QNAME, GetFileContentRequest.class, GetFileContent.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link com.gratex.perconik.services.ast.rcs.IsChangesetQueuedOrImportedResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.gratex.com/PerConIk/AstRcs/IEntityService", name = "IsChangesetQueuedOrImportedResult", scope = com.gratex.perconik.services.ast.rcs.entity.IsChangesetQueuedOrImportedResponse.class)
    public JAXBElement<com.gratex.perconik.services.ast.rcs.IsChangesetQueuedOrImportedResponse> createIsChangesetQueuedOrImportedResponseIsChangesetQueuedOrImportedResult(com.gratex.perconik.services.ast.rcs.IsChangesetQueuedOrImportedResponse value) {
        return new JAXBElement<com.gratex.perconik.services.ast.rcs.IsChangesetQueuedOrImportedResponse>(_IsChangesetQueuedOrImportedResponseIsChangesetQueuedOrImportedResult_QNAME, com.gratex.perconik.services.ast.rcs.IsChangesetQueuedOrImportedResponse.class, com.gratex.perconik.services.ast.rcs.entity.IsChangesetQueuedOrImportedResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link com.gratex.perconik.services.ast.rcs.GetFileResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.gratex.com/PerConIk/AstRcs/IEntityService", name = "GetFileResult", scope = com.gratex.perconik.services.ast.rcs.entity.GetFileResponse.class)
    public JAXBElement<com.gratex.perconik.services.ast.rcs.GetFileResponse> createGetFileResponseGetFileResult(com.gratex.perconik.services.ast.rcs.GetFileResponse value) {
        return new JAXBElement<com.gratex.perconik.services.ast.rcs.GetFileResponse>(_GetFileResponseGetFileResult_QNAME, com.gratex.perconik.services.ast.rcs.GetFileResponse.class, com.gratex.perconik.services.ast.rcs.entity.GetFileResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link com.gratex.perconik.services.ast.rcs.SearchChangesetsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.gratex.com/PerConIk/AstRcs/IEntityService", name = "SearchChangesetsResult", scope = com.gratex.perconik.services.ast.rcs.entity.SearchChangesetsResponse.class)
    public JAXBElement<com.gratex.perconik.services.ast.rcs.SearchChangesetsResponse> createSearchChangesetsResponseSearchChangesetsResult(com.gratex.perconik.services.ast.rcs.SearchChangesetsResponse value) {
        return new JAXBElement<com.gratex.perconik.services.ast.rcs.SearchChangesetsResponse>(_SearchChangesetsResponseSearchChangesetsResult_QNAME, com.gratex.perconik.services.ast.rcs.SearchChangesetsResponse.class, com.gratex.perconik.services.ast.rcs.entity.SearchChangesetsResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SearchCodeEntitiesRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.gratex.com/PerConIk/AstRcs/IEntityService", name = "request", scope = SearchCodeEntities.class)
    public JAXBElement<SearchCodeEntitiesRequest> createSearchCodeEntitiesRequest(SearchCodeEntitiesRequest value) {
        return new JAXBElement<SearchCodeEntitiesRequest>(_GetCodeEntityFullContextRequest_QNAME, SearchCodeEntitiesRequest.class, SearchCodeEntities.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link com.gratex.perconik.services.ast.rcs.GetRcsServerResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.gratex.com/PerConIk/AstRcs/IEntityService", name = "GetRcsServerResult", scope = com.gratex.perconik.services.ast.rcs.entity.GetRcsServerResponse.class)
    public JAXBElement<com.gratex.perconik.services.ast.rcs.GetRcsServerResponse> createGetRcsServerResponseGetRcsServerResult(com.gratex.perconik.services.ast.rcs.GetRcsServerResponse value) {
        return new JAXBElement<com.gratex.perconik.services.ast.rcs.GetRcsServerResponse>(_GetRcsServerResponseGetRcsServerResult_QNAME, com.gratex.perconik.services.ast.rcs.GetRcsServerResponse.class, com.gratex.perconik.services.ast.rcs.entity.GetRcsServerResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetImportCapabilitiesRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.gratex.com/PerConIk/AstRcs/IEntityService", name = "request", scope = GetImportCapabilities.class)
    public JAXBElement<GetImportCapabilitiesRequest> createGetImportCapabilitiesRequest(GetImportCapabilitiesRequest value) {
        return new JAXBElement<GetImportCapabilitiesRequest>(_GetCodeEntityFullContextRequest_QNAME, GetImportCapabilitiesRequest.class, GetImportCapabilities.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link com.gratex.perconik.services.ast.rcs.EnsureRcsServerResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.gratex.com/PerConIk/AstRcs/IEntityService", name = "EnsureRcsServerResult", scope = com.gratex.perconik.services.ast.rcs.entity.EnsureRcsServerResponse.class)
    public JAXBElement<com.gratex.perconik.services.ast.rcs.EnsureRcsServerResponse> createEnsureRcsServerResponseEnsureRcsServerResult(com.gratex.perconik.services.ast.rcs.EnsureRcsServerResponse value) {
        return new JAXBElement<com.gratex.perconik.services.ast.rcs.EnsureRcsServerResponse>(_EnsureRcsServerResponseEnsureRcsServerResult_QNAME, com.gratex.perconik.services.ast.rcs.EnsureRcsServerResponse.class, com.gratex.perconik.services.ast.rcs.entity.EnsureRcsServerResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetChangedFilesRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.gratex.com/PerConIk/AstRcs/IEntityService", name = "request", scope = GetChangedFiles.class)
    public JAXBElement<GetChangedFilesRequest> createGetChangedFilesRequest(GetChangedFilesRequest value) {
        return new JAXBElement<GetChangedFilesRequest>(_GetCodeEntityFullContextRequest_QNAME, GetChangedFilesRequest.class, GetChangedFiles.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link com.gratex.perconik.services.ast.rcs.GetImportCapabilitiesResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.gratex.com/PerConIk/AstRcs/IEntityService", name = "GetImportCapabilitiesResult", scope = com.gratex.perconik.services.ast.rcs.entity.GetImportCapabilitiesResponse.class)
    public JAXBElement<com.gratex.perconik.services.ast.rcs.GetImportCapabilitiesResponse> createGetImportCapabilitiesResponseGetImportCapabilitiesResult(com.gratex.perconik.services.ast.rcs.GetImportCapabilitiesResponse value) {
        return new JAXBElement<com.gratex.perconik.services.ast.rcs.GetImportCapabilitiesResponse>(_GetImportCapabilitiesResponseGetImportCapabilitiesResult_QNAME, com.gratex.perconik.services.ast.rcs.GetImportCapabilitiesResponse.class, com.gratex.perconik.services.ast.rcs.entity.GetImportCapabilitiesResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SearchTypeCodeEntitiesLightRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.gratex.com/PerConIk/AstRcs/IEntityService", name = "request", scope = SearchTypeCodeEntitiesLight.class)
    public JAXBElement<SearchTypeCodeEntitiesLightRequest> createSearchTypeCodeEntitiesLightRequest(SearchTypeCodeEntitiesLightRequest value) {
        return new JAXBElement<SearchTypeCodeEntitiesLightRequest>(_GetCodeEntityFullContextRequest_QNAME, SearchTypeCodeEntitiesLightRequest.class, SearchTypeCodeEntitiesLight.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link com.gratex.perconik.services.ast.rcs.GetChangedFilesResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.gratex.com/PerConIk/AstRcs/IEntityService", name = "GetChangedFilesResult", scope = com.gratex.perconik.services.ast.rcs.entity.GetChangedFilesResponse.class)
    public JAXBElement<com.gratex.perconik.services.ast.rcs.GetChangedFilesResponse> createGetChangedFilesResponseGetChangedFilesResult(com.gratex.perconik.services.ast.rcs.GetChangedFilesResponse value) {
        return new JAXBElement<com.gratex.perconik.services.ast.rcs.GetChangedFilesResponse>(_GetChangedFilesResponseGetChangedFilesResult_QNAME, com.gratex.perconik.services.ast.rcs.GetChangedFilesResponse.class, com.gratex.perconik.services.ast.rcs.entity.GetChangedFilesResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SearchChangesetsRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.gratex.com/PerConIk/AstRcs/IEntityService", name = "request", scope = SearchChangesets.class)
    public JAXBElement<SearchChangesetsRequest> createSearchChangesetsRequest(SearchChangesetsRequest value) {
        return new JAXBElement<SearchChangesetsRequest>(_GetCodeEntityFullContextRequest_QNAME, SearchChangesetsRequest.class, SearchChangesets.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link IsChangesetQueuedOrImportedRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.gratex.com/PerConIk/AstRcs/IEntityService", name = "request", scope = IsChangesetQueuedOrImported.class)
    public JAXBElement<IsChangesetQueuedOrImportedRequest> createIsChangesetQueuedOrImportedRequest(IsChangesetQueuedOrImportedRequest value) {
        return new JAXBElement<IsChangesetQueuedOrImportedRequest>(_GetCodeEntityFullContextRequest_QNAME, IsChangesetQueuedOrImportedRequest.class, IsChangesetQueuedOrImported.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link com.gratex.perconik.services.ast.rcs.SearchCodeEntitiesResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.gratex.com/PerConIk/AstRcs/IEntityService", name = "SearchCodeEntitiesResult", scope = com.gratex.perconik.services.ast.rcs.entity.SearchCodeEntitiesResponse.class)
    public JAXBElement<com.gratex.perconik.services.ast.rcs.SearchCodeEntitiesResponse> createSearchCodeEntitiesResponseSearchCodeEntitiesResult(com.gratex.perconik.services.ast.rcs.SearchCodeEntitiesResponse value) {
        return new JAXBElement<com.gratex.perconik.services.ast.rcs.SearchCodeEntitiesResponse>(_SearchCodeEntitiesResponseSearchCodeEntitiesResult_QNAME, com.gratex.perconik.services.ast.rcs.SearchCodeEntitiesResponse.class, com.gratex.perconik.services.ast.rcs.entity.SearchCodeEntitiesResponse.class, value);
    }

}
