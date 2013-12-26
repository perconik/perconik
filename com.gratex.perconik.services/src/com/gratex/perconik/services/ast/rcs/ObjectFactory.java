
package com.gratex.perconik.services.ast.rcs;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;
import com.gratex.perconik.services.ast.rcs.serialization.arrays.ArrayOfint;
import com.gratex.perconik.services.ast.rcs.serialization.arrays.ArrayOfstring;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.gratex.perconik.services.ast.rcs package. 
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

    private final static QName _ArrayOfRcsProjectDto_QNAME = new QName("http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", "ArrayOfRcsProjectDto");
    private final static QName _QueueChangesetForImportRequest_QNAME = new QName("http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", "QueueChangesetForImportRequest");
    private final static QName _FileGitIdentifierDto_QNAME = new QName("http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", "FileGitIdentifierDto");
    private final static QName _GetCodeEntityContentResponse_QNAME = new QName("http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", "GetCodeEntityContentResponse");
    private final static QName _QueueChangesetForImportResponse_QNAME = new QName("http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", "QueueChangesetForImportResponse");
    private final static QName _ArrayOfUserDto_QNAME = new QName("http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", "ArrayOfUserDto");
    private final static QName _GetChangesetRcsProjectResponse_QNAME = new QName("http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", "GetChangesetRcsProjectResponse");
    private final static QName _GetFileRequest_QNAME = new QName("http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", "GetFileRequest");
    private final static QName _GetFilesByTfsIdentifiersResponse_QNAME = new QName("http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", "GetFilesByTfsIdentifiersResponse");
    private final static QName _PagedRequest_QNAME = new QName("http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", "PagedRequest");
    private final static QName _ReturnValueDto_QNAME = new QName("http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", "ReturnValueDto");
    private final static QName _GetRcsServerResponse_QNAME = new QName("http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", "GetRcsServerResponse");
    private final static QName _ArrayOfFileGitIdentifierDto_QNAME = new QName("http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", "ArrayOfFileGitIdentifierDto");
    private final static QName _InheritanceDto_QNAME = new QName("http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", "InheritanceDto");
    private final static QName _GetFileResponse_QNAME = new QName("http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", "GetFileResponse");
    private final static QName _ArrayOfCodeEntityVersionDto_QNAME = new QName("http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", "ArrayOfCodeEntityVersionDto");
    private final static QName _GetBranchImportedHeadRequest_QNAME = new QName("http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", "GetBranchImportedHeadRequest");
    private final static QName _SearchCodeEntitiesRequest_QNAME = new QName("http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", "SearchCodeEntitiesRequest");
    private final static QName _GetCodeEntityRequest_QNAME = new QName("http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", "GetCodeEntityRequest");
    private final static QName _IsChangesetQueuedOrImportedRequest_QNAME = new QName("http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", "IsChangesetQueuedOrImportedRequest");
    private final static QName _EntityType_QNAME = new QName("http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", "EntityType");
    private final static QName _GetBranchQueuedHeadRequest_QNAME = new QName("http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", "GetBranchQueuedHeadRequest");
    private final static QName _SearchBranchesResponse_QNAME = new QName("http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", "SearchBranchesResponse");
    private final static QName _GetFilesByTfsIdentifiersRequest_QNAME = new QName("http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", "GetFilesByTfsIdentifiersRequest");
    private final static QName _GetChangedFilesRequest_QNAME = new QName("http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", "GetChangedFilesRequest");
    private final static QName _GetRcsProjectRequest_QNAME = new QName("http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", "GetRcsProjectRequest");
    private final static QName _ParameterDto_QNAME = new QName("http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", "ParameterDto");
    private final static QName _SearchFilesResponse_QNAME = new QName("http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", "SearchFilesResponse");
    private final static QName _GetRcsProjectResponse_QNAME = new QName("http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", "GetRcsProjectResponse");
    private final static QName _SearchRcsServersResponse_QNAME = new QName("http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", "SearchRcsServersResponse");
    private final static QName _ChangesetDto_QNAME = new QName("http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", "ChangesetDto");
    private final static QName _RcsServerDto_QNAME = new QName("http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", "RcsServerDto");
    private final static QName _ArrayOfInheritanceDto_QNAME = new QName("http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", "ArrayOfInheritanceDto");
    private final static QName _ArrayOfBranchDto_QNAME = new QName("http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", "ArrayOfBranchDto");
    private final static QName _GetUserRequest_QNAME = new QName("http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", "GetUserRequest");
    private final static QName _ChangeType_QNAME = new QName("http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", "ChangeType");
    private final static QName _PagedResponse_QNAME = new QName("http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", "PagedResponse");
    private final static QName _GetFileChangesetsRequest_QNAME = new QName("http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", "GetFileChangesetsRequest");
    private final static QName _GetFilesByGitIdentifiersRequest_QNAME = new QName("http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", "GetFilesByGitIdentifiersRequest");
    private final static QName _FileTfsIdentifierDto_QNAME = new QName("http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", "FileTfsIdentifierDto");
    private final static QName _SearchFoldersRequest_QNAME = new QName("http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", "SearchFoldersRequest");
    private final static QName _SearchFoldersResponse_QNAME = new QName("http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", "SearchFoldersResponse");
    private final static QName _GetCodeEntityFullContextResponse_QNAME = new QName("http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", "GetCodeEntityFullContextResponse");
    private final static QName _ArrayOfRcsServerDto_QNAME = new QName("http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", "ArrayOfRcsServerDto");
    private final static QName _SearchRcsProjectsRequest_QNAME = new QName("http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", "SearchRcsProjectsRequest");
    private final static QName _ArrayOfQueuedFileVersionDto_QNAME = new QName("http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", "ArrayOfQueuedFileVersionDto");
    private final static QName _SearchCodeEntitiesResponse_QNAME = new QName("http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", "SearchCodeEntitiesResponse");
    private final static QName _GetFileChangesetsResponse_QNAME = new QName("http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", "GetFileChangesetsResponse");
    private final static QName _FileVersionDto_QNAME = new QName("http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", "FileVersionDto");
    private final static QName _ArrayOfParameterDto_QNAME = new QName("http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", "ArrayOfParameterDto");
    private final static QName _EnsureRcsProjectResponse_QNAME = new QName("http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", "EnsureRcsProjectResponse");
    private final static QName _DeclarationDto_QNAME = new QName("http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", "DeclarationDto");
    private final static QName _EnsureBranchesResponse_QNAME = new QName("http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", "EnsureBranchesResponse");
    private final static QName _CodeEntityVersionIdDto_QNAME = new QName("http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", "CodeEntityVersionIdDto");
    private final static QName _SearchFilesRequest_QNAME = new QName("http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", "SearchFilesRequest");
    private final static QName _SearchRcsServersRequest_QNAME = new QName("http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", "SearchRcsServersRequest");
    private final static QName _EnsureRcsProjectRequest_QNAME = new QName("http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", "EnsureRcsProjectRequest");
    private final static QName _GetFileContentRequest_QNAME = new QName("http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", "GetFileContentRequest");
    private final static QName _GetChangesetRequest_QNAME = new QName("http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", "GetChangesetRequest");
    private final static QName _ArrayOfFileVersionDto_QNAME = new QName("http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", "ArrayOfFileVersionDto");
    private final static QName _UserDto_QNAME = new QName("http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", "UserDto");
    private final static QName _GetCodeEntityFullContextRequest_QNAME = new QName("http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", "GetCodeEntityFullContextRequest");
    private final static QName _ArrayOfChangesetDto_QNAME = new QName("http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", "ArrayOfChangesetDto");
    private final static QName _GetChildCodeEntitiesRequest_QNAME = new QName("http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", "GetChildCodeEntitiesRequest");
    private final static QName _GetCodeEntityContentRequest_QNAME = new QName("http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", "GetCodeEntityContentRequest");
    private final static QName _GetCodeEntityResponse_QNAME = new QName("http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", "GetCodeEntityResponse");
    private final static QName _GetUserResponse_QNAME = new QName("http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", "GetUserResponse");
    private final static QName _EnsureRcsServerResponse_QNAME = new QName("http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", "EnsureRcsServerResponse");
    private final static QName _ArrayOfFileTfsIdentifierDto_QNAME = new QName("http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", "ArrayOfFileTfsIdentifierDto");
    private final static QName _GetFileContentResponse_QNAME = new QName("http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", "GetFileContentResponse");
    private final static QName _SearchBranchesRequest_QNAME = new QName("http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", "SearchBranchesRequest");
    private final static QName _GetCodeEntityChangesetsResponse_QNAME = new QName("http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", "GetCodeEntityChangesetsResponse");
    private final static QName _GetChangesetRcsProjectRequest_QNAME = new QName("http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", "GetChangesetRcsProjectRequest");
    private final static QName _GetBranchQueuedHeadResponse_QNAME = new QName("http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", "GetBranchQueuedHeadResponse");
    private final static QName _AssociateChangesetWithBranchRequest_QNAME = new QName("http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", "AssociateChangesetWithBranchRequest");
    private final static QName _GetChangesetResponse_QNAME = new QName("http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", "GetChangesetResponse");
    private final static QName _SearchChangesetsResponse_QNAME = new QName("http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", "SearchChangesetsResponse");
    private final static QName _AssociateChangesetWithBranchResponse_QNAME = new QName("http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", "AssociateChangesetWithBranchResponse");
    private final static QName _GetRcsServerRequest_QNAME = new QName("http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", "GetRcsServerRequest");
    private final static QName _SearchUsersResponse_QNAME = new QName("http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", "SearchUsersResponse");
    private final static QName _RcsProjectDto_QNAME = new QName("http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", "RcsProjectDto");
    private final static QName _GetImportCapabilitiesResponse_QNAME = new QName("http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", "GetImportCapabilitiesResponse");
    private final static QName _EntityVersionDto_QNAME = new QName("http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", "EntityVersionDto");
    private final static QName _ArrayOfCodeEntityVersionIdDto_QNAME = new QName("http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", "ArrayOfCodeEntityVersionIdDto");
    private final static QName _SearchRcsProjectsResponse_QNAME = new QName("http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", "SearchRcsProjectsResponse");
    private final static QName _GetBranchResponse_QNAME = new QName("http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", "GetBranchResponse");
    private final static QName _IsChangesetQueuedOrImportedResponse_QNAME = new QName("http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", "IsChangesetQueuedOrImportedResponse");
    private final static QName _EnsureRcsServerRequest_QNAME = new QName("http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", "EnsureRcsServerRequest");
    private final static QName _GetChangedFilesResponse_QNAME = new QName("http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", "GetChangedFilesResponse");
    private final static QName _GetBranchImportedHeadResponse_QNAME = new QName("http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", "GetBranchImportedHeadResponse");
    private final static QName _SearchChangesetsRequest_QNAME = new QName("http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", "SearchChangesetsRequest");
    private final static QName _BranchDto_QNAME = new QName("http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", "BranchDto");
    private final static QName _GetCodeEntityChangesetsRequest_QNAME = new QName("http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", "GetCodeEntityChangesetsRequest");
    private final static QName _GetImportCapabilitiesRequest_QNAME = new QName("http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", "GetImportCapabilitiesRequest");
    private final static QName _CodeEntityVersionDto_QNAME = new QName("http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", "CodeEntityVersionDto");
    private final static QName _QueuedFileVersionDto_QNAME = new QName("http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", "QueuedFileVersionDto");
    private final static QName _GetChildCodeEntitiesResponse_QNAME = new QName("http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", "GetChildCodeEntitiesResponse");
    private final static QName _EnsureBranchesRequest_QNAME = new QName("http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", "EnsureBranchesRequest");
    private final static QName _SearchUsersRequest_QNAME = new QName("http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", "SearchUsersRequest");
    private final static QName _ChangesetQueuedOrImportedState_QNAME = new QName("http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", "ChangesetQueuedOrImportedState");
    private final static QName _GetBranchRequest_QNAME = new QName("http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", "GetBranchRequest");
    private final static QName _GetFilesByGitIdentifiersResponse_QNAME = new QName("http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", "GetFilesByGitIdentifiersResponse");
    private final static QName _RcsProjectDtoUrl_QNAME = new QName("http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", "Url");
    private final static QName _GetCodeEntityResponseVersion_QNAME = new QName("http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", "Version");
    private final static QName _EnsureBranchesResponseBranchIds_QNAME = new QName("http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", "BranchIds");
    private final static QName _SearchCodeEntitiesRequestEntityId_QNAME = new QName("http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", "EntityId");
    private final static QName _SearchCodeEntitiesRequestName_QNAME = new QName("http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", "Name");
    private final static QName _SearchCodeEntitiesRequestFileVersionId_QNAME = new QName("http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", "FileVersionId");
    private final static QName _SearchCodeEntitiesRequestChangesetId_QNAME = new QName("http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", "ChangesetId");
    private final static QName _AssociateChangesetWithBranchRequestChangesetIdInRcs_QNAME = new QName("http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", "ChangesetIdInRcs");
    private final static QName _SearchBranchesRequestRcsProjectId_QNAME = new QName("http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", "RcsProjectId");
    private final static QName _GetCodeEntityFullContextResponseParents_QNAME = new QName("http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", "Parents");
    private final static QName _GetCodeEntityFullContextResponseChildren_QNAME = new QName("http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", "Children");
    private final static QName _GetRcsProjectResponseRcsProject_QNAME = new QName("http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", "RcsProject");
    private final static QName _SearchChangesetsRequestCommitterId_QNAME = new QName("http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", "CommitterId");
    private final static QName _SearchChangesetsRequestCreatedFrom_QNAME = new QName("http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", "CreatedFrom");
    private final static QName _SearchChangesetsRequestCreatedTo_QNAME = new QName("http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", "CreatedTo");
    private final static QName _GetFilesByTfsIdentifiersResponseVersions_QNAME = new QName("http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", "Versions");
    private final static QName _EnsureBranchesRequestNames_QNAME = new QName("http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", "Names");
    private final static QName _EntityVersionDtoAncestor1Id_QNAME = new QName("http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", "Ancestor1Id");
    private final static QName _EntityVersionDtoAncestor1ChangeType_QNAME = new QName("http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", "Ancestor1ChangeType");
    private final static QName _EntityVersionDtoAncestor2Id_QNAME = new QName("http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", "Ancestor2Id");
    private final static QName _EntityVersionDtoAncestor2ChangeType_QNAME = new QName("http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", "Ancestor2ChangeType");
    private final static QName _DeclarationDtoModifier_QNAME = new QName("http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", "Modifier");
    private final static QName _DeclarationDtoInheritances_QNAME = new QName("http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", "Inheritances");
    private final static QName _DeclarationDtoParameters_QNAME = new QName("http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", "Parameters");
    private final static QName _DeclarationDtoReturnValue_QNAME = new QName("http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", "ReturnValue");
    private final static QName _GetImportCapabilitiesResponseSupportedFileExtensions_QNAME = new QName("http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", "SupportedFileExtensions");
    private final static QName _ChangesetDtoIdInRcs_QNAME = new QName("http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", "IdInRcs");
    private final static QName _ChangesetDtoMessage_QNAME = new QName("http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", "Message");
    private final static QName _ChangesetDtoCommitter_QNAME = new QName("http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", "Committer");
    private final static QName _GetChangedFilesResponseFileVersions_QNAME = new QName("http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", "FileVersions");
    private final static QName _SearchUsersRequestLogin_QNAME = new QName("http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", "Login");
    private final static QName _InheritanceDtoTypeName_QNAME = new QName("http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", "TypeName");
    private final static QName _SearchRcsProjectsRequestRcsServerId_QNAME = new QName("http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", "RcsServerId");
    private final static QName _CodeEntityVersionDtoDeclaration_QNAME = new QName("http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", "Declaration");
    private final static QName _CodeEntityVersionDtoCommentEndRow_QNAME = new QName("http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", "CommentEndRow");
    private final static QName _CodeEntityVersionDtoParentId_QNAME = new QName("http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", "ParentId");
    private final static QName _CodeEntityVersionDtoCommentStartRow_QNAME = new QName("http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", "CommentStartRow");
    private final static QName _FileGitIdentifierDtoBranchName_QNAME = new QName("http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", "BranchName");
    private final static QName _FileGitIdentifierDtoRcsProjectUrl_QNAME = new QName("http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", "RcsProjectUrl");
    private final static QName _GetRcsServerResponseRcsServer_QNAME = new QName("http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", "RcsServer");
    private final static QName _SearchBranchesResponseBranches_QNAME = new QName("http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", "Branches");
    private final static QName _GetFilesByGitIdentifiersRequestIdentifiers_QNAME = new QName("http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", "Identifiers");
    private final static QName _SearchCodeEntitiesResponseCodeEntityVersions_QNAME = new QName("http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", "CodeEntityVersions");
    private final static QName _GetBranchImportedHeadResponseChangeset_QNAME = new QName("http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", "Changeset");
    private final static QName _GetCodeEntityChangesetsResponseChangesets_QNAME = new QName("http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", "Changesets");
    private final static QName _GetFileContentResponseContent_QNAME = new QName("http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", "Content");
    private final static QName _SearchRcsServersResponseRcsServers_QNAME = new QName("http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", "RcsServers");
    private final static QName _SearchRcsProjectsResponseRcsProjects_QNAME = new QName("http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", "RcsProjects");
    private final static QName _GetUserResponseUser_QNAME = new QName("http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", "User");
    private final static QName _SearchFilesRequestUrlStart_QNAME = new QName("http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", "UrlStart");
    private final static QName _SearchUsersResponseUsers_QNAME = new QName("http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", "Users");
    private final static QName _QueueChangesetForImportRequestAncestor2IdInRcs_QNAME = new QName("http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", "Ancestor2IdInRcs");
    private final static QName _QueueChangesetForImportRequestChangedFiles_QNAME = new QName("http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", "ChangedFiles");
    private final static QName _QueueChangesetForImportRequestFilesBlob_QNAME = new QName("http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", "FilesBlob");
    private final static QName _QueueChangesetForImportRequestAncestor1IdInRcs_QNAME = new QName("http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", "Ancestor1IdInRcs");
    private final static QName _BranchDtoImportedHeadId_QNAME = new QName("http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", "ImportedHeadId");
    private final static QName _BranchDtoQueuedHeadId_QNAME = new QName("http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", "QueuedHeadId");
    private final static QName _GetBranchResponseBranch_QNAME = new QName("http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", "Branch");
    private final static QName _QueuedFileVersionDtoContentIdentifier_QNAME = new QName("http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", "ContentIdentifier");
    private final static QName _QueuedFileVersionDtoFileUrl_QNAME = new QName("http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", "FileUrl");
    private final static QName _RcsServerDtoType_QNAME = new QName("http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", "Type");
    private final static QName _EnsureRcsServerRequestRcsServerType_QNAME = new QName("http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", "RcsServerType");
    private final static QName _FileTfsIdentifierDtoFullUrl_QNAME = new QName("http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", "FullUrl");
    private final static QName _FileTfsIdentifierDtoRcsServerUrl_QNAME = new QName("http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", "RcsServerUrl");
    private final static QName _SearchFoldersResponseFolders_QNAME = new QName("http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", "Folders");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.gratex.perconik.services.ast.rcs
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetImportCapabilitiesResponse }
     * 
     */
    public GetImportCapabilitiesResponse createGetImportCapabilitiesResponse() {
        return new GetImportCapabilitiesResponse();
    }

    /**
     * Create an instance of {@link RcsProjectDto }
     * 
     */
    public RcsProjectDto createRcsProjectDto() {
        return new RcsProjectDto();
    }

    /**
     * Create an instance of {@link SearchUsersResponse }
     * 
     */
    public SearchUsersResponse createSearchUsersResponse() {
        return new SearchUsersResponse();
    }

    /**
     * Create an instance of {@link GetRcsServerRequest }
     * 
     */
    public GetRcsServerRequest createGetRcsServerRequest() {
        return new GetRcsServerRequest();
    }

    /**
     * Create an instance of {@link ArrayOfCodeEntityVersionIdDto }
     * 
     */
    public ArrayOfCodeEntityVersionIdDto createArrayOfCodeEntityVersionIdDto() {
        return new ArrayOfCodeEntityVersionIdDto();
    }

    /**
     * Create an instance of {@link EntityVersionDto }
     * 
     */
    public EntityVersionDto createEntityVersionDto() {
        return new EntityVersionDto();
    }

    /**
     * Create an instance of {@link AssociateChangesetWithBranchResponse }
     * 
     */
    public AssociateChangesetWithBranchResponse createAssociateChangesetWithBranchResponse() {
        return new AssociateChangesetWithBranchResponse();
    }

    /**
     * Create an instance of {@link GetBranchResponse }
     * 
     */
    public GetBranchResponse createGetBranchResponse() {
        return new GetBranchResponse();
    }

    /**
     * Create an instance of {@link EnsureRcsServerRequest }
     * 
     */
    public EnsureRcsServerRequest createEnsureRcsServerRequest() {
        return new EnsureRcsServerRequest();
    }

    /**
     * Create an instance of {@link IsChangesetQueuedOrImportedResponse }
     * 
     */
    public IsChangesetQueuedOrImportedResponse createIsChangesetQueuedOrImportedResponse() {
        return new IsChangesetQueuedOrImportedResponse();
    }

    /**
     * Create an instance of {@link SearchRcsProjectsResponse }
     * 
     */
    public SearchRcsProjectsResponse createSearchRcsProjectsResponse() {
        return new SearchRcsProjectsResponse();
    }

    /**
     * Create an instance of {@link GetImportCapabilitiesRequest }
     * 
     */
    public GetImportCapabilitiesRequest createGetImportCapabilitiesRequest() {
        return new GetImportCapabilitiesRequest();
    }

    /**
     * Create an instance of {@link BranchDto }
     * 
     */
    public BranchDto createBranchDto() {
        return new BranchDto();
    }

    /**
     * Create an instance of {@link GetCodeEntityChangesetsRequest }
     * 
     */
    public GetCodeEntityChangesetsRequest createGetCodeEntityChangesetsRequest() {
        return new GetCodeEntityChangesetsRequest();
    }

    /**
     * Create an instance of {@link SearchChangesetsRequest }
     * 
     */
    public SearchChangesetsRequest createSearchChangesetsRequest() {
        return new SearchChangesetsRequest();
    }

    /**
     * Create an instance of {@link CodeEntityVersionDto }
     * 
     */
    public CodeEntityVersionDto createCodeEntityVersionDto() {
        return new CodeEntityVersionDto();
    }

    /**
     * Create an instance of {@link GetChangedFilesResponse }
     * 
     */
    public GetChangedFilesResponse createGetChangedFilesResponse() {
        return new GetChangedFilesResponse();
    }

    /**
     * Create an instance of {@link GetBranchImportedHeadResponse }
     * 
     */
    public GetBranchImportedHeadResponse createGetBranchImportedHeadResponse() {
        return new GetBranchImportedHeadResponse();
    }

    /**
     * Create an instance of {@link GetFilesByGitIdentifiersResponse }
     * 
     */
    public GetFilesByGitIdentifiersResponse createGetFilesByGitIdentifiersResponse() {
        return new GetFilesByGitIdentifiersResponse();
    }

    /**
     * Create an instance of {@link GetBranchRequest }
     * 
     */
    public GetBranchRequest createGetBranchRequest() {
        return new GetBranchRequest();
    }

    /**
     * Create an instance of {@link GetChildCodeEntitiesResponse }
     * 
     */
    public GetChildCodeEntitiesResponse createGetChildCodeEntitiesResponse() {
        return new GetChildCodeEntitiesResponse();
    }

    /**
     * Create an instance of {@link QueuedFileVersionDto }
     * 
     */
    public QueuedFileVersionDto createQueuedFileVersionDto() {
        return new QueuedFileVersionDto();
    }

    /**
     * Create an instance of {@link SearchUsersRequest }
     * 
     */
    public SearchUsersRequest createSearchUsersRequest() {
        return new SearchUsersRequest();
    }

    /**
     * Create an instance of {@link EnsureBranchesRequest }
     * 
     */
    public EnsureBranchesRequest createEnsureBranchesRequest() {
        return new EnsureBranchesRequest();
    }

    /**
     * Create an instance of {@link CodeEntityVersionIdDto }
     * 
     */
    public CodeEntityVersionIdDto createCodeEntityVersionIdDto() {
        return new CodeEntityVersionIdDto();
    }

    /**
     * Create an instance of {@link SearchRcsServersRequest }
     * 
     */
    public SearchRcsServersRequest createSearchRcsServersRequest() {
        return new SearchRcsServersRequest();
    }

    /**
     * Create an instance of {@link SearchFilesRequest }
     * 
     */
    public SearchFilesRequest createSearchFilesRequest() {
        return new SearchFilesRequest();
    }

    /**
     * Create an instance of {@link EnsureBranchesResponse }
     * 
     */
    public EnsureBranchesResponse createEnsureBranchesResponse() {
        return new EnsureBranchesResponse();
    }

    /**
     * Create an instance of {@link GetCodeEntityFullContextRequest }
     * 
     */
    public GetCodeEntityFullContextRequest createGetCodeEntityFullContextRequest() {
        return new GetCodeEntityFullContextRequest();
    }

    /**
     * Create an instance of {@link UserDto }
     * 
     */
    public UserDto createUserDto() {
        return new UserDto();
    }

    /**
     * Create an instance of {@link ArrayOfFileVersionDto }
     * 
     */
    public ArrayOfFileVersionDto createArrayOfFileVersionDto() {
        return new ArrayOfFileVersionDto();
    }

    /**
     * Create an instance of {@link GetChildCodeEntitiesRequest }
     * 
     */
    public GetChildCodeEntitiesRequest createGetChildCodeEntitiesRequest() {
        return new GetChildCodeEntitiesRequest();
    }

    /**
     * Create an instance of {@link ArrayOfChangesetDto }
     * 
     */
    public ArrayOfChangesetDto createArrayOfChangesetDto() {
        return new ArrayOfChangesetDto();
    }

    /**
     * Create an instance of {@link GetFileContentRequest }
     * 
     */
    public GetFileContentRequest createGetFileContentRequest() {
        return new GetFileContentRequest();
    }

    /**
     * Create an instance of {@link EnsureRcsProjectRequest }
     * 
     */
    public EnsureRcsProjectRequest createEnsureRcsProjectRequest() {
        return new EnsureRcsProjectRequest();
    }

    /**
     * Create an instance of {@link GetChangesetRequest }
     * 
     */
    public GetChangesetRequest createGetChangesetRequest() {
        return new GetChangesetRequest();
    }

    /**
     * Create an instance of {@link GetCodeEntityResponse }
     * 
     */
    public GetCodeEntityResponse createGetCodeEntityResponse() {
        return new GetCodeEntityResponse();
    }

    /**
     * Create an instance of {@link GetFileContentResponse }
     * 
     */
    public GetFileContentResponse createGetFileContentResponse() {
        return new GetFileContentResponse();
    }

    /**
     * Create an instance of {@link ArrayOfFileTfsIdentifierDto }
     * 
     */
    public ArrayOfFileTfsIdentifierDto createArrayOfFileTfsIdentifierDto() {
        return new ArrayOfFileTfsIdentifierDto();
    }

    /**
     * Create an instance of {@link EnsureRcsServerResponse }
     * 
     */
    public EnsureRcsServerResponse createEnsureRcsServerResponse() {
        return new EnsureRcsServerResponse();
    }

    /**
     * Create an instance of {@link GetUserResponse }
     * 
     */
    public GetUserResponse createGetUserResponse() {
        return new GetUserResponse();
    }

    /**
     * Create an instance of {@link GetCodeEntityContentRequest }
     * 
     */
    public GetCodeEntityContentRequest createGetCodeEntityContentRequest() {
        return new GetCodeEntityContentRequest();
    }

    /**
     * Create an instance of {@link AssociateChangesetWithBranchRequest }
     * 
     */
    public AssociateChangesetWithBranchRequest createAssociateChangesetWithBranchRequest() {
        return new AssociateChangesetWithBranchRequest();
    }

    /**
     * Create an instance of {@link GetBranchQueuedHeadResponse }
     * 
     */
    public GetBranchQueuedHeadResponse createGetBranchQueuedHeadResponse() {
        return new GetBranchQueuedHeadResponse();
    }

    /**
     * Create an instance of {@link GetChangesetRcsProjectRequest }
     * 
     */
    public GetChangesetRcsProjectRequest createGetChangesetRcsProjectRequest() {
        return new GetChangesetRcsProjectRequest();
    }

    /**
     * Create an instance of {@link SearchChangesetsResponse }
     * 
     */
    public SearchChangesetsResponse createSearchChangesetsResponse() {
        return new SearchChangesetsResponse();
    }

    /**
     * Create an instance of {@link GetChangesetResponse }
     * 
     */
    public GetChangesetResponse createGetChangesetResponse() {
        return new GetChangesetResponse();
    }

    /**
     * Create an instance of {@link GetCodeEntityChangesetsResponse }
     * 
     */
    public GetCodeEntityChangesetsResponse createGetCodeEntityChangesetsResponse() {
        return new GetCodeEntityChangesetsResponse();
    }

    /**
     * Create an instance of {@link SearchBranchesRequest }
     * 
     */
    public SearchBranchesRequest createSearchBranchesRequest() {
        return new SearchBranchesRequest();
    }

    /**
     * Create an instance of {@link GetFileChangesetsRequest }
     * 
     */
    public GetFileChangesetsRequest createGetFileChangesetsRequest() {
        return new GetFileChangesetsRequest();
    }

    /**
     * Create an instance of {@link GetFilesByGitIdentifiersRequest }
     * 
     */
    public GetFilesByGitIdentifiersRequest createGetFilesByGitIdentifiersRequest() {
        return new GetFilesByGitIdentifiersRequest();
    }

    /**
     * Create an instance of {@link RcsServerDto }
     * 
     */
    public RcsServerDto createRcsServerDto() {
        return new RcsServerDto();
    }

    /**
     * Create an instance of {@link ChangesetDto }
     * 
     */
    public ChangesetDto createChangesetDto() {
        return new ChangesetDto();
    }

    /**
     * Create an instance of {@link SearchRcsServersResponse }
     * 
     */
    public SearchRcsServersResponse createSearchRcsServersResponse() {
        return new SearchRcsServersResponse();
    }

    /**
     * Create an instance of {@link PagedResponse }
     * 
     */
    public PagedResponse createPagedResponse() {
        return new PagedResponse();
    }

    /**
     * Create an instance of {@link GetUserRequest }
     * 
     */
    public GetUserRequest createGetUserRequest() {
        return new GetUserRequest();
    }

    /**
     * Create an instance of {@link ArrayOfInheritanceDto }
     * 
     */
    public ArrayOfInheritanceDto createArrayOfInheritanceDto() {
        return new ArrayOfInheritanceDto();
    }

    /**
     * Create an instance of {@link ArrayOfBranchDto }
     * 
     */
    public ArrayOfBranchDto createArrayOfBranchDto() {
        return new ArrayOfBranchDto();
    }

    /**
     * Create an instance of {@link ArrayOfRcsServerDto }
     * 
     */
    public ArrayOfRcsServerDto createArrayOfRcsServerDto() {
        return new ArrayOfRcsServerDto();
    }

    /**
     * Create an instance of {@link GetCodeEntityFullContextResponse }
     * 
     */
    public GetCodeEntityFullContextResponse createGetCodeEntityFullContextResponse() {
        return new GetCodeEntityFullContextResponse();
    }

    /**
     * Create an instance of {@link SearchFoldersResponse }
     * 
     */
    public SearchFoldersResponse createSearchFoldersResponse() {
        return new SearchFoldersResponse();
    }

    /**
     * Create an instance of {@link FileTfsIdentifierDto }
     * 
     */
    public FileTfsIdentifierDto createFileTfsIdentifierDto() {
        return new FileTfsIdentifierDto();
    }

    /**
     * Create an instance of {@link SearchFoldersRequest }
     * 
     */
    public SearchFoldersRequest createSearchFoldersRequest() {
        return new SearchFoldersRequest();
    }

    /**
     * Create an instance of {@link FileVersionDto }
     * 
     */
    public FileVersionDto createFileVersionDto() {
        return new FileVersionDto();
    }

    /**
     * Create an instance of {@link GetFileChangesetsResponse }
     * 
     */
    public GetFileChangesetsResponse createGetFileChangesetsResponse() {
        return new GetFileChangesetsResponse();
    }

    /**
     * Create an instance of {@link SearchRcsProjectsRequest }
     * 
     */
    public SearchRcsProjectsRequest createSearchRcsProjectsRequest() {
        return new SearchRcsProjectsRequest();
    }

    /**
     * Create an instance of {@link SearchCodeEntitiesResponse }
     * 
     */
    public SearchCodeEntitiesResponse createSearchCodeEntitiesResponse() {
        return new SearchCodeEntitiesResponse();
    }

    /**
     * Create an instance of {@link ArrayOfQueuedFileVersionDto }
     * 
     */
    public ArrayOfQueuedFileVersionDto createArrayOfQueuedFileVersionDto() {
        return new ArrayOfQueuedFileVersionDto();
    }

    /**
     * Create an instance of {@link EnsureRcsProjectResponse }
     * 
     */
    public EnsureRcsProjectResponse createEnsureRcsProjectResponse() {
        return new EnsureRcsProjectResponse();
    }

    /**
     * Create an instance of {@link ArrayOfParameterDto }
     * 
     */
    public ArrayOfParameterDto createArrayOfParameterDto() {
        return new ArrayOfParameterDto();
    }

    /**
     * Create an instance of {@link DeclarationDto }
     * 
     */
    public DeclarationDto createDeclarationDto() {
        return new DeclarationDto();
    }

    /**
     * Create an instance of {@link GetCodeEntityContentResponse }
     * 
     */
    public GetCodeEntityContentResponse createGetCodeEntityContentResponse() {
        return new GetCodeEntityContentResponse();
    }

    /**
     * Create an instance of {@link QueueChangesetForImportResponse }
     * 
     */
    public QueueChangesetForImportResponse createQueueChangesetForImportResponse() {
        return new QueueChangesetForImportResponse();
    }

    /**
     * Create an instance of {@link ArrayOfUserDto }
     * 
     */
    public ArrayOfUserDto createArrayOfUserDto() {
        return new ArrayOfUserDto();
    }

    /**
     * Create an instance of {@link GetChangesetRcsProjectResponse }
     * 
     */
    public GetChangesetRcsProjectResponse createGetChangesetRcsProjectResponse() {
        return new GetChangesetRcsProjectResponse();
    }

    /**
     * Create an instance of {@link GetFileRequest }
     * 
     */
    public GetFileRequest createGetFileRequest() {
        return new GetFileRequest();
    }

    /**
     * Create an instance of {@link QueueChangesetForImportRequest }
     * 
     */
    public QueueChangesetForImportRequest createQueueChangesetForImportRequest() {
        return new QueueChangesetForImportRequest();
    }

    /**
     * Create an instance of {@link ArrayOfRcsProjectDto }
     * 
     */
    public ArrayOfRcsProjectDto createArrayOfRcsProjectDto() {
        return new ArrayOfRcsProjectDto();
    }

    /**
     * Create an instance of {@link FileGitIdentifierDto }
     * 
     */
    public FileGitIdentifierDto createFileGitIdentifierDto() {
        return new FileGitIdentifierDto();
    }

    /**
     * Create an instance of {@link ReturnValueDto }
     * 
     */
    public ReturnValueDto createReturnValueDto() {
        return new ReturnValueDto();
    }

    /**
     * Create an instance of {@link InheritanceDto }
     * 
     */
    public InheritanceDto createInheritanceDto() {
        return new InheritanceDto();
    }

    /**
     * Create an instance of {@link ArrayOfFileGitIdentifierDto }
     * 
     */
    public ArrayOfFileGitIdentifierDto createArrayOfFileGitIdentifierDto() {
        return new ArrayOfFileGitIdentifierDto();
    }

    /**
     * Create an instance of {@link GetRcsServerResponse }
     * 
     */
    public GetRcsServerResponse createGetRcsServerResponse() {
        return new GetRcsServerResponse();
    }

    /**
     * Create an instance of {@link GetFilesByTfsIdentifiersResponse }
     * 
     */
    public GetFilesByTfsIdentifiersResponse createGetFilesByTfsIdentifiersResponse() {
        return new GetFilesByTfsIdentifiersResponse();
    }

    /**
     * Create an instance of {@link PagedRequest }
     * 
     */
    public PagedRequest createPagedRequest() {
        return new PagedRequest();
    }

    /**
     * Create an instance of {@link GetCodeEntityRequest }
     * 
     */
    public GetCodeEntityRequest createGetCodeEntityRequest() {
        return new GetCodeEntityRequest();
    }

    /**
     * Create an instance of {@link IsChangesetQueuedOrImportedRequest }
     * 
     */
    public IsChangesetQueuedOrImportedRequest createIsChangesetQueuedOrImportedRequest() {
        return new IsChangesetQueuedOrImportedRequest();
    }

    /**
     * Create an instance of {@link SearchCodeEntitiesRequest }
     * 
     */
    public SearchCodeEntitiesRequest createSearchCodeEntitiesRequest() {
        return new SearchCodeEntitiesRequest();
    }

    /**
     * Create an instance of {@link ArrayOfCodeEntityVersionDto }
     * 
     */
    public ArrayOfCodeEntityVersionDto createArrayOfCodeEntityVersionDto() {
        return new ArrayOfCodeEntityVersionDto();
    }

    /**
     * Create an instance of {@link GetFileResponse }
     * 
     */
    public GetFileResponse createGetFileResponse() {
        return new GetFileResponse();
    }

    /**
     * Create an instance of {@link GetBranchImportedHeadRequest }
     * 
     */
    public GetBranchImportedHeadRequest createGetBranchImportedHeadRequest() {
        return new GetBranchImportedHeadRequest();
    }

    /**
     * Create an instance of {@link ParameterDto }
     * 
     */
    public ParameterDto createParameterDto() {
        return new ParameterDto();
    }

    /**
     * Create an instance of {@link GetRcsProjectRequest }
     * 
     */
    public GetRcsProjectRequest createGetRcsProjectRequest() {
        return new GetRcsProjectRequest();
    }

    /**
     * Create an instance of {@link GetRcsProjectResponse }
     * 
     */
    public GetRcsProjectResponse createGetRcsProjectResponse() {
        return new GetRcsProjectResponse();
    }

    /**
     * Create an instance of {@link SearchFilesResponse }
     * 
     */
    public SearchFilesResponse createSearchFilesResponse() {
        return new SearchFilesResponse();
    }

    /**
     * Create an instance of {@link GetChangedFilesRequest }
     * 
     */
    public GetChangedFilesRequest createGetChangedFilesRequest() {
        return new GetChangedFilesRequest();
    }

    /**
     * Create an instance of {@link GetFilesByTfsIdentifiersRequest }
     * 
     */
    public GetFilesByTfsIdentifiersRequest createGetFilesByTfsIdentifiersRequest() {
        return new GetFilesByTfsIdentifiersRequest();
    }

    /**
     * Create an instance of {@link SearchBranchesResponse }
     * 
     */
    public SearchBranchesResponse createSearchBranchesResponse() {
        return new SearchBranchesResponse();
    }

    /**
     * Create an instance of {@link GetBranchQueuedHeadRequest }
     * 
     */
    public GetBranchQueuedHeadRequest createGetBranchQueuedHeadRequest() {
        return new GetBranchQueuedHeadRequest();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfRcsProjectDto }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", name = "ArrayOfRcsProjectDto")
    public JAXBElement<ArrayOfRcsProjectDto> createArrayOfRcsProjectDto(ArrayOfRcsProjectDto value) {
        return new JAXBElement<ArrayOfRcsProjectDto>(_ArrayOfRcsProjectDto_QNAME, ArrayOfRcsProjectDto.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link QueueChangesetForImportRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", name = "QueueChangesetForImportRequest")
    public JAXBElement<QueueChangesetForImportRequest> createQueueChangesetForImportRequest(QueueChangesetForImportRequest value) {
        return new JAXBElement<QueueChangesetForImportRequest>(_QueueChangesetForImportRequest_QNAME, QueueChangesetForImportRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FileGitIdentifierDto }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", name = "FileGitIdentifierDto")
    public JAXBElement<FileGitIdentifierDto> createFileGitIdentifierDto(FileGitIdentifierDto value) {
        return new JAXBElement<FileGitIdentifierDto>(_FileGitIdentifierDto_QNAME, FileGitIdentifierDto.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetCodeEntityContentResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", name = "GetCodeEntityContentResponse")
    public JAXBElement<GetCodeEntityContentResponse> createGetCodeEntityContentResponse(GetCodeEntityContentResponse value) {
        return new JAXBElement<GetCodeEntityContentResponse>(_GetCodeEntityContentResponse_QNAME, GetCodeEntityContentResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link QueueChangesetForImportResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", name = "QueueChangesetForImportResponse")
    public JAXBElement<QueueChangesetForImportResponse> createQueueChangesetForImportResponse(QueueChangesetForImportResponse value) {
        return new JAXBElement<QueueChangesetForImportResponse>(_QueueChangesetForImportResponse_QNAME, QueueChangesetForImportResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfUserDto }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", name = "ArrayOfUserDto")
    public JAXBElement<ArrayOfUserDto> createArrayOfUserDto(ArrayOfUserDto value) {
        return new JAXBElement<ArrayOfUserDto>(_ArrayOfUserDto_QNAME, ArrayOfUserDto.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetChangesetRcsProjectResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", name = "GetChangesetRcsProjectResponse")
    public JAXBElement<GetChangesetRcsProjectResponse> createGetChangesetRcsProjectResponse(GetChangesetRcsProjectResponse value) {
        return new JAXBElement<GetChangesetRcsProjectResponse>(_GetChangesetRcsProjectResponse_QNAME, GetChangesetRcsProjectResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetFileRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", name = "GetFileRequest")
    public JAXBElement<GetFileRequest> createGetFileRequest(GetFileRequest value) {
        return new JAXBElement<GetFileRequest>(_GetFileRequest_QNAME, GetFileRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetFilesByTfsIdentifiersResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", name = "GetFilesByTfsIdentifiersResponse")
    public JAXBElement<GetFilesByTfsIdentifiersResponse> createGetFilesByTfsIdentifiersResponse(GetFilesByTfsIdentifiersResponse value) {
        return new JAXBElement<GetFilesByTfsIdentifiersResponse>(_GetFilesByTfsIdentifiersResponse_QNAME, GetFilesByTfsIdentifiersResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PagedRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", name = "PagedRequest")
    public JAXBElement<PagedRequest> createPagedRequest(PagedRequest value) {
        return new JAXBElement<PagedRequest>(_PagedRequest_QNAME, PagedRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ReturnValueDto }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", name = "ReturnValueDto")
    public JAXBElement<ReturnValueDto> createReturnValueDto(ReturnValueDto value) {
        return new JAXBElement<ReturnValueDto>(_ReturnValueDto_QNAME, ReturnValueDto.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetRcsServerResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", name = "GetRcsServerResponse")
    public JAXBElement<GetRcsServerResponse> createGetRcsServerResponse(GetRcsServerResponse value) {
        return new JAXBElement<GetRcsServerResponse>(_GetRcsServerResponse_QNAME, GetRcsServerResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfFileGitIdentifierDto }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", name = "ArrayOfFileGitIdentifierDto")
    public JAXBElement<ArrayOfFileGitIdentifierDto> createArrayOfFileGitIdentifierDto(ArrayOfFileGitIdentifierDto value) {
        return new JAXBElement<ArrayOfFileGitIdentifierDto>(_ArrayOfFileGitIdentifierDto_QNAME, ArrayOfFileGitIdentifierDto.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InheritanceDto }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", name = "InheritanceDto")
    public JAXBElement<InheritanceDto> createInheritanceDto(InheritanceDto value) {
        return new JAXBElement<InheritanceDto>(_InheritanceDto_QNAME, InheritanceDto.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetFileResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", name = "GetFileResponse")
    public JAXBElement<GetFileResponse> createGetFileResponse(GetFileResponse value) {
        return new JAXBElement<GetFileResponse>(_GetFileResponse_QNAME, GetFileResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfCodeEntityVersionDto }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", name = "ArrayOfCodeEntityVersionDto")
    public JAXBElement<ArrayOfCodeEntityVersionDto> createArrayOfCodeEntityVersionDto(ArrayOfCodeEntityVersionDto value) {
        return new JAXBElement<ArrayOfCodeEntityVersionDto>(_ArrayOfCodeEntityVersionDto_QNAME, ArrayOfCodeEntityVersionDto.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetBranchImportedHeadRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", name = "GetBranchImportedHeadRequest")
    public JAXBElement<GetBranchImportedHeadRequest> createGetBranchImportedHeadRequest(GetBranchImportedHeadRequest value) {
        return new JAXBElement<GetBranchImportedHeadRequest>(_GetBranchImportedHeadRequest_QNAME, GetBranchImportedHeadRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SearchCodeEntitiesRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", name = "SearchCodeEntitiesRequest")
    public JAXBElement<SearchCodeEntitiesRequest> createSearchCodeEntitiesRequest(SearchCodeEntitiesRequest value) {
        return new JAXBElement<SearchCodeEntitiesRequest>(_SearchCodeEntitiesRequest_QNAME, SearchCodeEntitiesRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetCodeEntityRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", name = "GetCodeEntityRequest")
    public JAXBElement<GetCodeEntityRequest> createGetCodeEntityRequest(GetCodeEntityRequest value) {
        return new JAXBElement<GetCodeEntityRequest>(_GetCodeEntityRequest_QNAME, GetCodeEntityRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link IsChangesetQueuedOrImportedRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", name = "IsChangesetQueuedOrImportedRequest")
    public JAXBElement<IsChangesetQueuedOrImportedRequest> createIsChangesetQueuedOrImportedRequest(IsChangesetQueuedOrImportedRequest value) {
        return new JAXBElement<IsChangesetQueuedOrImportedRequest>(_IsChangesetQueuedOrImportedRequest_QNAME, IsChangesetQueuedOrImportedRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EntityType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", name = "EntityType")
    public JAXBElement<EntityType> createEntityType(EntityType value) {
        return new JAXBElement<EntityType>(_EntityType_QNAME, EntityType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetBranchQueuedHeadRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", name = "GetBranchQueuedHeadRequest")
    public JAXBElement<GetBranchQueuedHeadRequest> createGetBranchQueuedHeadRequest(GetBranchQueuedHeadRequest value) {
        return new JAXBElement<GetBranchQueuedHeadRequest>(_GetBranchQueuedHeadRequest_QNAME, GetBranchQueuedHeadRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SearchBranchesResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", name = "SearchBranchesResponse")
    public JAXBElement<SearchBranchesResponse> createSearchBranchesResponse(SearchBranchesResponse value) {
        return new JAXBElement<SearchBranchesResponse>(_SearchBranchesResponse_QNAME, SearchBranchesResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetFilesByTfsIdentifiersRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", name = "GetFilesByTfsIdentifiersRequest")
    public JAXBElement<GetFilesByTfsIdentifiersRequest> createGetFilesByTfsIdentifiersRequest(GetFilesByTfsIdentifiersRequest value) {
        return new JAXBElement<GetFilesByTfsIdentifiersRequest>(_GetFilesByTfsIdentifiersRequest_QNAME, GetFilesByTfsIdentifiersRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetChangedFilesRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", name = "GetChangedFilesRequest")
    public JAXBElement<GetChangedFilesRequest> createGetChangedFilesRequest(GetChangedFilesRequest value) {
        return new JAXBElement<GetChangedFilesRequest>(_GetChangedFilesRequest_QNAME, GetChangedFilesRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetRcsProjectRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", name = "GetRcsProjectRequest")
    public JAXBElement<GetRcsProjectRequest> createGetRcsProjectRequest(GetRcsProjectRequest value) {
        return new JAXBElement<GetRcsProjectRequest>(_GetRcsProjectRequest_QNAME, GetRcsProjectRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ParameterDto }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", name = "ParameterDto")
    public JAXBElement<ParameterDto> createParameterDto(ParameterDto value) {
        return new JAXBElement<ParameterDto>(_ParameterDto_QNAME, ParameterDto.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SearchFilesResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", name = "SearchFilesResponse")
    public JAXBElement<SearchFilesResponse> createSearchFilesResponse(SearchFilesResponse value) {
        return new JAXBElement<SearchFilesResponse>(_SearchFilesResponse_QNAME, SearchFilesResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetRcsProjectResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", name = "GetRcsProjectResponse")
    public JAXBElement<GetRcsProjectResponse> createGetRcsProjectResponse(GetRcsProjectResponse value) {
        return new JAXBElement<GetRcsProjectResponse>(_GetRcsProjectResponse_QNAME, GetRcsProjectResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SearchRcsServersResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", name = "SearchRcsServersResponse")
    public JAXBElement<SearchRcsServersResponse> createSearchRcsServersResponse(SearchRcsServersResponse value) {
        return new JAXBElement<SearchRcsServersResponse>(_SearchRcsServersResponse_QNAME, SearchRcsServersResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ChangesetDto }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", name = "ChangesetDto")
    public JAXBElement<ChangesetDto> createChangesetDto(ChangesetDto value) {
        return new JAXBElement<ChangesetDto>(_ChangesetDto_QNAME, ChangesetDto.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RcsServerDto }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", name = "RcsServerDto")
    public JAXBElement<RcsServerDto> createRcsServerDto(RcsServerDto value) {
        return new JAXBElement<RcsServerDto>(_RcsServerDto_QNAME, RcsServerDto.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfInheritanceDto }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", name = "ArrayOfInheritanceDto")
    public JAXBElement<ArrayOfInheritanceDto> createArrayOfInheritanceDto(ArrayOfInheritanceDto value) {
        return new JAXBElement<ArrayOfInheritanceDto>(_ArrayOfInheritanceDto_QNAME, ArrayOfInheritanceDto.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfBranchDto }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", name = "ArrayOfBranchDto")
    public JAXBElement<ArrayOfBranchDto> createArrayOfBranchDto(ArrayOfBranchDto value) {
        return new JAXBElement<ArrayOfBranchDto>(_ArrayOfBranchDto_QNAME, ArrayOfBranchDto.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetUserRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", name = "GetUserRequest")
    public JAXBElement<GetUserRequest> createGetUserRequest(GetUserRequest value) {
        return new JAXBElement<GetUserRequest>(_GetUserRequest_QNAME, GetUserRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ChangeType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", name = "ChangeType")
    public JAXBElement<ChangeType> createChangeType(ChangeType value) {
        return new JAXBElement<ChangeType>(_ChangeType_QNAME, ChangeType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PagedResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", name = "PagedResponse")
    public JAXBElement<PagedResponse> createPagedResponse(PagedResponse value) {
        return new JAXBElement<PagedResponse>(_PagedResponse_QNAME, PagedResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetFileChangesetsRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", name = "GetFileChangesetsRequest")
    public JAXBElement<GetFileChangesetsRequest> createGetFileChangesetsRequest(GetFileChangesetsRequest value) {
        return new JAXBElement<GetFileChangesetsRequest>(_GetFileChangesetsRequest_QNAME, GetFileChangesetsRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetFilesByGitIdentifiersRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", name = "GetFilesByGitIdentifiersRequest")
    public JAXBElement<GetFilesByGitIdentifiersRequest> createGetFilesByGitIdentifiersRequest(GetFilesByGitIdentifiersRequest value) {
        return new JAXBElement<GetFilesByGitIdentifiersRequest>(_GetFilesByGitIdentifiersRequest_QNAME, GetFilesByGitIdentifiersRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FileTfsIdentifierDto }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", name = "FileTfsIdentifierDto")
    public JAXBElement<FileTfsIdentifierDto> createFileTfsIdentifierDto(FileTfsIdentifierDto value) {
        return new JAXBElement<FileTfsIdentifierDto>(_FileTfsIdentifierDto_QNAME, FileTfsIdentifierDto.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SearchFoldersRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", name = "SearchFoldersRequest")
    public JAXBElement<SearchFoldersRequest> createSearchFoldersRequest(SearchFoldersRequest value) {
        return new JAXBElement<SearchFoldersRequest>(_SearchFoldersRequest_QNAME, SearchFoldersRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SearchFoldersResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", name = "SearchFoldersResponse")
    public JAXBElement<SearchFoldersResponse> createSearchFoldersResponse(SearchFoldersResponse value) {
        return new JAXBElement<SearchFoldersResponse>(_SearchFoldersResponse_QNAME, SearchFoldersResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetCodeEntityFullContextResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", name = "GetCodeEntityFullContextResponse")
    public JAXBElement<GetCodeEntityFullContextResponse> createGetCodeEntityFullContextResponse(GetCodeEntityFullContextResponse value) {
        return new JAXBElement<GetCodeEntityFullContextResponse>(_GetCodeEntityFullContextResponse_QNAME, GetCodeEntityFullContextResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfRcsServerDto }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", name = "ArrayOfRcsServerDto")
    public JAXBElement<ArrayOfRcsServerDto> createArrayOfRcsServerDto(ArrayOfRcsServerDto value) {
        return new JAXBElement<ArrayOfRcsServerDto>(_ArrayOfRcsServerDto_QNAME, ArrayOfRcsServerDto.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SearchRcsProjectsRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", name = "SearchRcsProjectsRequest")
    public JAXBElement<SearchRcsProjectsRequest> createSearchRcsProjectsRequest(SearchRcsProjectsRequest value) {
        return new JAXBElement<SearchRcsProjectsRequest>(_SearchRcsProjectsRequest_QNAME, SearchRcsProjectsRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfQueuedFileVersionDto }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", name = "ArrayOfQueuedFileVersionDto")
    public JAXBElement<ArrayOfQueuedFileVersionDto> createArrayOfQueuedFileVersionDto(ArrayOfQueuedFileVersionDto value) {
        return new JAXBElement<ArrayOfQueuedFileVersionDto>(_ArrayOfQueuedFileVersionDto_QNAME, ArrayOfQueuedFileVersionDto.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SearchCodeEntitiesResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", name = "SearchCodeEntitiesResponse")
    public JAXBElement<SearchCodeEntitiesResponse> createSearchCodeEntitiesResponse(SearchCodeEntitiesResponse value) {
        return new JAXBElement<SearchCodeEntitiesResponse>(_SearchCodeEntitiesResponse_QNAME, SearchCodeEntitiesResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetFileChangesetsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", name = "GetFileChangesetsResponse")
    public JAXBElement<GetFileChangesetsResponse> createGetFileChangesetsResponse(GetFileChangesetsResponse value) {
        return new JAXBElement<GetFileChangesetsResponse>(_GetFileChangesetsResponse_QNAME, GetFileChangesetsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FileVersionDto }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", name = "FileVersionDto")
    public JAXBElement<FileVersionDto> createFileVersionDto(FileVersionDto value) {
        return new JAXBElement<FileVersionDto>(_FileVersionDto_QNAME, FileVersionDto.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfParameterDto }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", name = "ArrayOfParameterDto")
    public JAXBElement<ArrayOfParameterDto> createArrayOfParameterDto(ArrayOfParameterDto value) {
        return new JAXBElement<ArrayOfParameterDto>(_ArrayOfParameterDto_QNAME, ArrayOfParameterDto.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EnsureRcsProjectResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", name = "EnsureRcsProjectResponse")
    public JAXBElement<EnsureRcsProjectResponse> createEnsureRcsProjectResponse(EnsureRcsProjectResponse value) {
        return new JAXBElement<EnsureRcsProjectResponse>(_EnsureRcsProjectResponse_QNAME, EnsureRcsProjectResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeclarationDto }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", name = "DeclarationDto")
    public JAXBElement<DeclarationDto> createDeclarationDto(DeclarationDto value) {
        return new JAXBElement<DeclarationDto>(_DeclarationDto_QNAME, DeclarationDto.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EnsureBranchesResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", name = "EnsureBranchesResponse")
    public JAXBElement<EnsureBranchesResponse> createEnsureBranchesResponse(EnsureBranchesResponse value) {
        return new JAXBElement<EnsureBranchesResponse>(_EnsureBranchesResponse_QNAME, EnsureBranchesResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CodeEntityVersionIdDto }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", name = "CodeEntityVersionIdDto")
    public JAXBElement<CodeEntityVersionIdDto> createCodeEntityVersionIdDto(CodeEntityVersionIdDto value) {
        return new JAXBElement<CodeEntityVersionIdDto>(_CodeEntityVersionIdDto_QNAME, CodeEntityVersionIdDto.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SearchFilesRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", name = "SearchFilesRequest")
    public JAXBElement<SearchFilesRequest> createSearchFilesRequest(SearchFilesRequest value) {
        return new JAXBElement<SearchFilesRequest>(_SearchFilesRequest_QNAME, SearchFilesRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SearchRcsServersRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", name = "SearchRcsServersRequest")
    public JAXBElement<SearchRcsServersRequest> createSearchRcsServersRequest(SearchRcsServersRequest value) {
        return new JAXBElement<SearchRcsServersRequest>(_SearchRcsServersRequest_QNAME, SearchRcsServersRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EnsureRcsProjectRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", name = "EnsureRcsProjectRequest")
    public JAXBElement<EnsureRcsProjectRequest> createEnsureRcsProjectRequest(EnsureRcsProjectRequest value) {
        return new JAXBElement<EnsureRcsProjectRequest>(_EnsureRcsProjectRequest_QNAME, EnsureRcsProjectRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetFileContentRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", name = "GetFileContentRequest")
    public JAXBElement<GetFileContentRequest> createGetFileContentRequest(GetFileContentRequest value) {
        return new JAXBElement<GetFileContentRequest>(_GetFileContentRequest_QNAME, GetFileContentRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetChangesetRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", name = "GetChangesetRequest")
    public JAXBElement<GetChangesetRequest> createGetChangesetRequest(GetChangesetRequest value) {
        return new JAXBElement<GetChangesetRequest>(_GetChangesetRequest_QNAME, GetChangesetRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfFileVersionDto }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", name = "ArrayOfFileVersionDto")
    public JAXBElement<ArrayOfFileVersionDto> createArrayOfFileVersionDto(ArrayOfFileVersionDto value) {
        return new JAXBElement<ArrayOfFileVersionDto>(_ArrayOfFileVersionDto_QNAME, ArrayOfFileVersionDto.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UserDto }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", name = "UserDto")
    public JAXBElement<UserDto> createUserDto(UserDto value) {
        return new JAXBElement<UserDto>(_UserDto_QNAME, UserDto.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetCodeEntityFullContextRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", name = "GetCodeEntityFullContextRequest")
    public JAXBElement<GetCodeEntityFullContextRequest> createGetCodeEntityFullContextRequest(GetCodeEntityFullContextRequest value) {
        return new JAXBElement<GetCodeEntityFullContextRequest>(_GetCodeEntityFullContextRequest_QNAME, GetCodeEntityFullContextRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfChangesetDto }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", name = "ArrayOfChangesetDto")
    public JAXBElement<ArrayOfChangesetDto> createArrayOfChangesetDto(ArrayOfChangesetDto value) {
        return new JAXBElement<ArrayOfChangesetDto>(_ArrayOfChangesetDto_QNAME, ArrayOfChangesetDto.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetChildCodeEntitiesRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", name = "GetChildCodeEntitiesRequest")
    public JAXBElement<GetChildCodeEntitiesRequest> createGetChildCodeEntitiesRequest(GetChildCodeEntitiesRequest value) {
        return new JAXBElement<GetChildCodeEntitiesRequest>(_GetChildCodeEntitiesRequest_QNAME, GetChildCodeEntitiesRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetCodeEntityContentRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", name = "GetCodeEntityContentRequest")
    public JAXBElement<GetCodeEntityContentRequest> createGetCodeEntityContentRequest(GetCodeEntityContentRequest value) {
        return new JAXBElement<GetCodeEntityContentRequest>(_GetCodeEntityContentRequest_QNAME, GetCodeEntityContentRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetCodeEntityResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", name = "GetCodeEntityResponse")
    public JAXBElement<GetCodeEntityResponse> createGetCodeEntityResponse(GetCodeEntityResponse value) {
        return new JAXBElement<GetCodeEntityResponse>(_GetCodeEntityResponse_QNAME, GetCodeEntityResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetUserResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", name = "GetUserResponse")
    public JAXBElement<GetUserResponse> createGetUserResponse(GetUserResponse value) {
        return new JAXBElement<GetUserResponse>(_GetUserResponse_QNAME, GetUserResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EnsureRcsServerResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", name = "EnsureRcsServerResponse")
    public JAXBElement<EnsureRcsServerResponse> createEnsureRcsServerResponse(EnsureRcsServerResponse value) {
        return new JAXBElement<EnsureRcsServerResponse>(_EnsureRcsServerResponse_QNAME, EnsureRcsServerResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfFileTfsIdentifierDto }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", name = "ArrayOfFileTfsIdentifierDto")
    public JAXBElement<ArrayOfFileTfsIdentifierDto> createArrayOfFileTfsIdentifierDto(ArrayOfFileTfsIdentifierDto value) {
        return new JAXBElement<ArrayOfFileTfsIdentifierDto>(_ArrayOfFileTfsIdentifierDto_QNAME, ArrayOfFileTfsIdentifierDto.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetFileContentResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", name = "GetFileContentResponse")
    public JAXBElement<GetFileContentResponse> createGetFileContentResponse(GetFileContentResponse value) {
        return new JAXBElement<GetFileContentResponse>(_GetFileContentResponse_QNAME, GetFileContentResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SearchBranchesRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", name = "SearchBranchesRequest")
    public JAXBElement<SearchBranchesRequest> createSearchBranchesRequest(SearchBranchesRequest value) {
        return new JAXBElement<SearchBranchesRequest>(_SearchBranchesRequest_QNAME, SearchBranchesRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetCodeEntityChangesetsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", name = "GetCodeEntityChangesetsResponse")
    public JAXBElement<GetCodeEntityChangesetsResponse> createGetCodeEntityChangesetsResponse(GetCodeEntityChangesetsResponse value) {
        return new JAXBElement<GetCodeEntityChangesetsResponse>(_GetCodeEntityChangesetsResponse_QNAME, GetCodeEntityChangesetsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetChangesetRcsProjectRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", name = "GetChangesetRcsProjectRequest")
    public JAXBElement<GetChangesetRcsProjectRequest> createGetChangesetRcsProjectRequest(GetChangesetRcsProjectRequest value) {
        return new JAXBElement<GetChangesetRcsProjectRequest>(_GetChangesetRcsProjectRequest_QNAME, GetChangesetRcsProjectRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetBranchQueuedHeadResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", name = "GetBranchQueuedHeadResponse")
    public JAXBElement<GetBranchQueuedHeadResponse> createGetBranchQueuedHeadResponse(GetBranchQueuedHeadResponse value) {
        return new JAXBElement<GetBranchQueuedHeadResponse>(_GetBranchQueuedHeadResponse_QNAME, GetBranchQueuedHeadResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AssociateChangesetWithBranchRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", name = "AssociateChangesetWithBranchRequest")
    public JAXBElement<AssociateChangesetWithBranchRequest> createAssociateChangesetWithBranchRequest(AssociateChangesetWithBranchRequest value) {
        return new JAXBElement<AssociateChangesetWithBranchRequest>(_AssociateChangesetWithBranchRequest_QNAME, AssociateChangesetWithBranchRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetChangesetResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", name = "GetChangesetResponse")
    public JAXBElement<GetChangesetResponse> createGetChangesetResponse(GetChangesetResponse value) {
        return new JAXBElement<GetChangesetResponse>(_GetChangesetResponse_QNAME, GetChangesetResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SearchChangesetsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", name = "SearchChangesetsResponse")
    public JAXBElement<SearchChangesetsResponse> createSearchChangesetsResponse(SearchChangesetsResponse value) {
        return new JAXBElement<SearchChangesetsResponse>(_SearchChangesetsResponse_QNAME, SearchChangesetsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AssociateChangesetWithBranchResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", name = "AssociateChangesetWithBranchResponse")
    public JAXBElement<AssociateChangesetWithBranchResponse> createAssociateChangesetWithBranchResponse(AssociateChangesetWithBranchResponse value) {
        return new JAXBElement<AssociateChangesetWithBranchResponse>(_AssociateChangesetWithBranchResponse_QNAME, AssociateChangesetWithBranchResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetRcsServerRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", name = "GetRcsServerRequest")
    public JAXBElement<GetRcsServerRequest> createGetRcsServerRequest(GetRcsServerRequest value) {
        return new JAXBElement<GetRcsServerRequest>(_GetRcsServerRequest_QNAME, GetRcsServerRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SearchUsersResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", name = "SearchUsersResponse")
    public JAXBElement<SearchUsersResponse> createSearchUsersResponse(SearchUsersResponse value) {
        return new JAXBElement<SearchUsersResponse>(_SearchUsersResponse_QNAME, SearchUsersResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RcsProjectDto }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", name = "RcsProjectDto")
    public JAXBElement<RcsProjectDto> createRcsProjectDto(RcsProjectDto value) {
        return new JAXBElement<RcsProjectDto>(_RcsProjectDto_QNAME, RcsProjectDto.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetImportCapabilitiesResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", name = "GetImportCapabilitiesResponse")
    public JAXBElement<GetImportCapabilitiesResponse> createGetImportCapabilitiesResponse(GetImportCapabilitiesResponse value) {
        return new JAXBElement<GetImportCapabilitiesResponse>(_GetImportCapabilitiesResponse_QNAME, GetImportCapabilitiesResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EntityVersionDto }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", name = "EntityVersionDto")
    public JAXBElement<EntityVersionDto> createEntityVersionDto(EntityVersionDto value) {
        return new JAXBElement<EntityVersionDto>(_EntityVersionDto_QNAME, EntityVersionDto.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfCodeEntityVersionIdDto }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", name = "ArrayOfCodeEntityVersionIdDto")
    public JAXBElement<ArrayOfCodeEntityVersionIdDto> createArrayOfCodeEntityVersionIdDto(ArrayOfCodeEntityVersionIdDto value) {
        return new JAXBElement<ArrayOfCodeEntityVersionIdDto>(_ArrayOfCodeEntityVersionIdDto_QNAME, ArrayOfCodeEntityVersionIdDto.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SearchRcsProjectsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", name = "SearchRcsProjectsResponse")
    public JAXBElement<SearchRcsProjectsResponse> createSearchRcsProjectsResponse(SearchRcsProjectsResponse value) {
        return new JAXBElement<SearchRcsProjectsResponse>(_SearchRcsProjectsResponse_QNAME, SearchRcsProjectsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetBranchResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", name = "GetBranchResponse")
    public JAXBElement<GetBranchResponse> createGetBranchResponse(GetBranchResponse value) {
        return new JAXBElement<GetBranchResponse>(_GetBranchResponse_QNAME, GetBranchResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link IsChangesetQueuedOrImportedResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", name = "IsChangesetQueuedOrImportedResponse")
    public JAXBElement<IsChangesetQueuedOrImportedResponse> createIsChangesetQueuedOrImportedResponse(IsChangesetQueuedOrImportedResponse value) {
        return new JAXBElement<IsChangesetQueuedOrImportedResponse>(_IsChangesetQueuedOrImportedResponse_QNAME, IsChangesetQueuedOrImportedResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EnsureRcsServerRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", name = "EnsureRcsServerRequest")
    public JAXBElement<EnsureRcsServerRequest> createEnsureRcsServerRequest(EnsureRcsServerRequest value) {
        return new JAXBElement<EnsureRcsServerRequest>(_EnsureRcsServerRequest_QNAME, EnsureRcsServerRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetChangedFilesResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", name = "GetChangedFilesResponse")
    public JAXBElement<GetChangedFilesResponse> createGetChangedFilesResponse(GetChangedFilesResponse value) {
        return new JAXBElement<GetChangedFilesResponse>(_GetChangedFilesResponse_QNAME, GetChangedFilesResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetBranchImportedHeadResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", name = "GetBranchImportedHeadResponse")
    public JAXBElement<GetBranchImportedHeadResponse> createGetBranchImportedHeadResponse(GetBranchImportedHeadResponse value) {
        return new JAXBElement<GetBranchImportedHeadResponse>(_GetBranchImportedHeadResponse_QNAME, GetBranchImportedHeadResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SearchChangesetsRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", name = "SearchChangesetsRequest")
    public JAXBElement<SearchChangesetsRequest> createSearchChangesetsRequest(SearchChangesetsRequest value) {
        return new JAXBElement<SearchChangesetsRequest>(_SearchChangesetsRequest_QNAME, SearchChangesetsRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BranchDto }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", name = "BranchDto")
    public JAXBElement<BranchDto> createBranchDto(BranchDto value) {
        return new JAXBElement<BranchDto>(_BranchDto_QNAME, BranchDto.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetCodeEntityChangesetsRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", name = "GetCodeEntityChangesetsRequest")
    public JAXBElement<GetCodeEntityChangesetsRequest> createGetCodeEntityChangesetsRequest(GetCodeEntityChangesetsRequest value) {
        return new JAXBElement<GetCodeEntityChangesetsRequest>(_GetCodeEntityChangesetsRequest_QNAME, GetCodeEntityChangesetsRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetImportCapabilitiesRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", name = "GetImportCapabilitiesRequest")
    public JAXBElement<GetImportCapabilitiesRequest> createGetImportCapabilitiesRequest(GetImportCapabilitiesRequest value) {
        return new JAXBElement<GetImportCapabilitiesRequest>(_GetImportCapabilitiesRequest_QNAME, GetImportCapabilitiesRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CodeEntityVersionDto }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", name = "CodeEntityVersionDto")
    public JAXBElement<CodeEntityVersionDto> createCodeEntityVersionDto(CodeEntityVersionDto value) {
        return new JAXBElement<CodeEntityVersionDto>(_CodeEntityVersionDto_QNAME, CodeEntityVersionDto.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link QueuedFileVersionDto }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", name = "QueuedFileVersionDto")
    public JAXBElement<QueuedFileVersionDto> createQueuedFileVersionDto(QueuedFileVersionDto value) {
        return new JAXBElement<QueuedFileVersionDto>(_QueuedFileVersionDto_QNAME, QueuedFileVersionDto.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetChildCodeEntitiesResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", name = "GetChildCodeEntitiesResponse")
    public JAXBElement<GetChildCodeEntitiesResponse> createGetChildCodeEntitiesResponse(GetChildCodeEntitiesResponse value) {
        return new JAXBElement<GetChildCodeEntitiesResponse>(_GetChildCodeEntitiesResponse_QNAME, GetChildCodeEntitiesResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EnsureBranchesRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", name = "EnsureBranchesRequest")
    public JAXBElement<EnsureBranchesRequest> createEnsureBranchesRequest(EnsureBranchesRequest value) {
        return new JAXBElement<EnsureBranchesRequest>(_EnsureBranchesRequest_QNAME, EnsureBranchesRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link SearchUsersRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", name = "SearchUsersRequest")
    public JAXBElement<SearchUsersRequest> createSearchUsersRequest(SearchUsersRequest value) {
        return new JAXBElement<SearchUsersRequest>(_SearchUsersRequest_QNAME, SearchUsersRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ChangesetQueuedOrImportedState }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", name = "ChangesetQueuedOrImportedState")
    public JAXBElement<ChangesetQueuedOrImportedState> createChangesetQueuedOrImportedState(ChangesetQueuedOrImportedState value) {
        return new JAXBElement<ChangesetQueuedOrImportedState>(_ChangesetQueuedOrImportedState_QNAME, ChangesetQueuedOrImportedState.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetBranchRequest }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", name = "GetBranchRequest")
    public JAXBElement<GetBranchRequest> createGetBranchRequest(GetBranchRequest value) {
        return new JAXBElement<GetBranchRequest>(_GetBranchRequest_QNAME, GetBranchRequest.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetFilesByGitIdentifiersResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", name = "GetFilesByGitIdentifiersResponse")
    public JAXBElement<GetFilesByGitIdentifiersResponse> createGetFilesByGitIdentifiersResponse(GetFilesByGitIdentifiersResponse value) {
        return new JAXBElement<GetFilesByGitIdentifiersResponse>(_GetFilesByGitIdentifiersResponse_QNAME, GetFilesByGitIdentifiersResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", name = "Url", scope = RcsProjectDto.class)
    public JAXBElement<String> createRcsProjectDtoUrl(String value) {
        return new JAXBElement<String>(_RcsProjectDtoUrl_QNAME, String.class, RcsProjectDto.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CodeEntityVersionDto }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", name = "Version", scope = GetCodeEntityResponse.class)
    public JAXBElement<CodeEntityVersionDto> createGetCodeEntityResponseVersion(CodeEntityVersionDto value) {
        return new JAXBElement<CodeEntityVersionDto>(_GetCodeEntityResponseVersion_QNAME, CodeEntityVersionDto.class, GetCodeEntityResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfint }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", name = "BranchIds", scope = EnsureBranchesResponse.class)
    public JAXBElement<ArrayOfint> createEnsureBranchesResponseBranchIds(ArrayOfint value) {
        return new JAXBElement<ArrayOfint>(_EnsureBranchesResponseBranchIds_QNAME, ArrayOfint.class, EnsureBranchesResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link EntityType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", name = "EntityType", scope = SearchCodeEntitiesRequest.class)
    public JAXBElement<EntityType> createSearchCodeEntitiesRequestEntityType(EntityType value) {
        return new JAXBElement<EntityType>(_EntityType_QNAME, EntityType.class, SearchCodeEntitiesRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", name = "EntityId", scope = SearchCodeEntitiesRequest.class)
    public JAXBElement<Integer> createSearchCodeEntitiesRequestEntityId(Integer value) {
        return new JAXBElement<Integer>(_SearchCodeEntitiesRequestEntityId_QNAME, Integer.class, SearchCodeEntitiesRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", name = "Name", scope = SearchCodeEntitiesRequest.class)
    public JAXBElement<String> createSearchCodeEntitiesRequestName(String value) {
        return new JAXBElement<String>(_SearchCodeEntitiesRequestName_QNAME, String.class, SearchCodeEntitiesRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", name = "FileVersionId", scope = SearchCodeEntitiesRequest.class)
    public JAXBElement<Integer> createSearchCodeEntitiesRequestFileVersionId(Integer value) {
        return new JAXBElement<Integer>(_SearchCodeEntitiesRequestFileVersionId_QNAME, Integer.class, SearchCodeEntitiesRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", name = "ChangesetId", scope = SearchCodeEntitiesRequest.class)
    public JAXBElement<Integer> createSearchCodeEntitiesRequestChangesetId(Integer value) {
        return new JAXBElement<Integer>(_SearchCodeEntitiesRequestChangesetId_QNAME, Integer.class, SearchCodeEntitiesRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", name = "ChangesetIdInRcs", scope = AssociateChangesetWithBranchRequest.class)
    public JAXBElement<String> createAssociateChangesetWithBranchRequestChangesetIdInRcs(String value) {
        return new JAXBElement<String>(_AssociateChangesetWithBranchRequestChangesetIdInRcs_QNAME, String.class, AssociateChangesetWithBranchRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", name = "Name", scope = SearchBranchesRequest.class)
    public JAXBElement<String> createSearchBranchesRequestName(String value) {
        return new JAXBElement<String>(_SearchCodeEntitiesRequestName_QNAME, String.class, SearchBranchesRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", name = "RcsProjectId", scope = SearchBranchesRequest.class)
    public JAXBElement<Integer> createSearchBranchesRequestRcsProjectId(Integer value) {
        return new JAXBElement<Integer>(_SearchBranchesRequestRcsProjectId_QNAME, Integer.class, SearchBranchesRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", name = "Url", scope = SearchFoldersRequest.class)
    public JAXBElement<String> createSearchFoldersRequestUrl(String value) {
        return new JAXBElement<String>(_RcsProjectDtoUrl_QNAME, String.class, SearchFoldersRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfCodeEntityVersionIdDto }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", name = "Parents", scope = GetCodeEntityFullContextResponse.class)
    public JAXBElement<ArrayOfCodeEntityVersionIdDto> createGetCodeEntityFullContextResponseParents(ArrayOfCodeEntityVersionIdDto value) {
        return new JAXBElement<ArrayOfCodeEntityVersionIdDto>(_GetCodeEntityFullContextResponseParents_QNAME, ArrayOfCodeEntityVersionIdDto.class, GetCodeEntityFullContextResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfCodeEntityVersionIdDto }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", name = "Children", scope = GetCodeEntityFullContextResponse.class)
    public JAXBElement<ArrayOfCodeEntityVersionIdDto> createGetCodeEntityFullContextResponseChildren(ArrayOfCodeEntityVersionIdDto value) {
        return new JAXBElement<ArrayOfCodeEntityVersionIdDto>(_GetCodeEntityFullContextResponseChildren_QNAME, ArrayOfCodeEntityVersionIdDto.class, GetCodeEntityFullContextResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RcsProjectDto }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", name = "RcsProject", scope = GetRcsProjectResponse.class)
    public JAXBElement<RcsProjectDto> createGetRcsProjectResponseRcsProject(RcsProjectDto value) {
        return new JAXBElement<RcsProjectDto>(_GetRcsProjectResponseRcsProject_QNAME, RcsProjectDto.class, GetRcsProjectResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", name = "CommitterId", scope = SearchChangesetsRequest.class)
    public JAXBElement<Integer> createSearchChangesetsRequestCommitterId(Integer value) {
        return new JAXBElement<Integer>(_SearchChangesetsRequestCommitterId_QNAME, Integer.class, SearchChangesetsRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", name = "ChangesetIdInRcs", scope = SearchChangesetsRequest.class)
    public JAXBElement<String> createSearchChangesetsRequestChangesetIdInRcs(String value) {
        return new JAXBElement<String>(_AssociateChangesetWithBranchRequestChangesetIdInRcs_QNAME, String.class, SearchChangesetsRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", name = "CreatedFrom", scope = SearchChangesetsRequest.class)
    public JAXBElement<XMLGregorianCalendar> createSearchChangesetsRequestCreatedFrom(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_SearchChangesetsRequestCreatedFrom_QNAME, XMLGregorianCalendar.class, SearchChangesetsRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", name = "CreatedTo", scope = SearchChangesetsRequest.class)
    public JAXBElement<XMLGregorianCalendar> createSearchChangesetsRequestCreatedTo(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_SearchChangesetsRequestCreatedTo_QNAME, XMLGregorianCalendar.class, SearchChangesetsRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfFileVersionDto }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", name = "Versions", scope = GetFilesByTfsIdentifiersResponse.class)
    public JAXBElement<ArrayOfFileVersionDto> createGetFilesByTfsIdentifiersResponseVersions(ArrayOfFileVersionDto value) {
        return new JAXBElement<ArrayOfFileVersionDto>(_GetFilesByTfsIdentifiersResponseVersions_QNAME, ArrayOfFileVersionDto.class, GetFilesByTfsIdentifiersResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfstring }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", name = "Names", scope = EnsureBranchesRequest.class)
    public JAXBElement<ArrayOfstring> createEnsureBranchesRequestNames(ArrayOfstring value) {
        return new JAXBElement<ArrayOfstring>(_EnsureBranchesRequestNames_QNAME, ArrayOfstring.class, EnsureBranchesRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", name = "Ancestor1Id", scope = EntityVersionDto.class)
    public JAXBElement<Integer> createEntityVersionDtoAncestor1Id(Integer value) {
        return new JAXBElement<Integer>(_EntityVersionDtoAncestor1Id_QNAME, Integer.class, EntityVersionDto.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ChangeType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", name = "Ancestor1ChangeType", scope = EntityVersionDto.class)
    public JAXBElement<ChangeType> createEntityVersionDtoAncestor1ChangeType(ChangeType value) {
        return new JAXBElement<ChangeType>(_EntityVersionDtoAncestor1ChangeType_QNAME, ChangeType.class, EntityVersionDto.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", name = "Ancestor2Id", scope = EntityVersionDto.class)
    public JAXBElement<Integer> createEntityVersionDtoAncestor2Id(Integer value) {
        return new JAXBElement<Integer>(_EntityVersionDtoAncestor2Id_QNAME, Integer.class, EntityVersionDto.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ChangeType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", name = "Ancestor2ChangeType", scope = EntityVersionDto.class)
    public JAXBElement<ChangeType> createEntityVersionDtoAncestor2ChangeType(ChangeType value) {
        return new JAXBElement<ChangeType>(_EntityVersionDtoAncestor2ChangeType_QNAME, ChangeType.class, EntityVersionDto.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", name = "Modifier", scope = DeclarationDto.class)
    public JAXBElement<String> createDeclarationDtoModifier(String value) {
        return new JAXBElement<String>(_DeclarationDtoModifier_QNAME, String.class, DeclarationDto.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfInheritanceDto }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", name = "Inheritances", scope = DeclarationDto.class)
    public JAXBElement<ArrayOfInheritanceDto> createDeclarationDtoInheritances(ArrayOfInheritanceDto value) {
        return new JAXBElement<ArrayOfInheritanceDto>(_DeclarationDtoInheritances_QNAME, ArrayOfInheritanceDto.class, DeclarationDto.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfParameterDto }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", name = "Parameters", scope = DeclarationDto.class)
    public JAXBElement<ArrayOfParameterDto> createDeclarationDtoParameters(ArrayOfParameterDto value) {
        return new JAXBElement<ArrayOfParameterDto>(_DeclarationDtoParameters_QNAME, ArrayOfParameterDto.class, DeclarationDto.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", name = "Name", scope = DeclarationDto.class)
    public JAXBElement<String> createDeclarationDtoName(String value) {
        return new JAXBElement<String>(_SearchCodeEntitiesRequestName_QNAME, String.class, DeclarationDto.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ReturnValueDto }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", name = "ReturnValue", scope = DeclarationDto.class)
    public JAXBElement<ReturnValueDto> createDeclarationDtoReturnValue(ReturnValueDto value) {
        return new JAXBElement<ReturnValueDto>(_DeclarationDtoReturnValue_QNAME, ReturnValueDto.class, DeclarationDto.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfstring }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", name = "SupportedFileExtensions", scope = GetImportCapabilitiesResponse.class)
    public JAXBElement<ArrayOfstring> createGetImportCapabilitiesResponseSupportedFileExtensions(ArrayOfstring value) {
        return new JAXBElement<ArrayOfstring>(_GetImportCapabilitiesResponseSupportedFileExtensions_QNAME, ArrayOfstring.class, GetImportCapabilitiesResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfFileVersionDto }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", name = "Versions", scope = GetFilesByGitIdentifiersResponse.class)
    public JAXBElement<ArrayOfFileVersionDto> createGetFilesByGitIdentifiersResponseVersions(ArrayOfFileVersionDto value) {
        return new JAXBElement<ArrayOfFileVersionDto>(_GetFilesByTfsIdentifiersResponseVersions_QNAME, ArrayOfFileVersionDto.class, GetFilesByGitIdentifiersResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", name = "IdInRcs", scope = ChangesetDto.class)
    public JAXBElement<String> createChangesetDtoIdInRcs(String value) {
        return new JAXBElement<String>(_ChangesetDtoIdInRcs_QNAME, String.class, ChangesetDto.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", name = "Ancestor1Id", scope = ChangesetDto.class)
    public JAXBElement<Integer> createChangesetDtoAncestor1Id(Integer value) {
        return new JAXBElement<Integer>(_EntityVersionDtoAncestor1Id_QNAME, Integer.class, ChangesetDto.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", name = "Ancestor2Id", scope = ChangesetDto.class)
    public JAXBElement<Integer> createChangesetDtoAncestor2Id(Integer value) {
        return new JAXBElement<Integer>(_EntityVersionDtoAncestor2Id_QNAME, Integer.class, ChangesetDto.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", name = "Message", scope = ChangesetDto.class)
    public JAXBElement<String> createChangesetDtoMessage(String value) {
        return new JAXBElement<String>(_ChangesetDtoMessage_QNAME, String.class, ChangesetDto.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UserDto }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", name = "Committer", scope = ChangesetDto.class)
    public JAXBElement<UserDto> createChangesetDtoCommitter(UserDto value) {
        return new JAXBElement<UserDto>(_ChangesetDtoCommitter_QNAME, UserDto.class, ChangesetDto.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfFileVersionDto }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", name = "FileVersions", scope = GetChangedFilesResponse.class)
    public JAXBElement<ArrayOfFileVersionDto> createGetChangedFilesResponseFileVersions(ArrayOfFileVersionDto value) {
        return new JAXBElement<ArrayOfFileVersionDto>(_GetChangedFilesResponseFileVersions_QNAME, ArrayOfFileVersionDto.class, GetChangedFilesResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", name = "Login", scope = SearchUsersRequest.class)
    public JAXBElement<String> createSearchUsersRequestLogin(String value) {
        return new JAXBElement<String>(_SearchUsersRequestLogin_QNAME, String.class, SearchUsersRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", name = "TypeName", scope = InheritanceDto.class)
    public JAXBElement<String> createInheritanceDtoTypeName(String value) {
        return new JAXBElement<String>(_InheritanceDtoTypeName_QNAME, String.class, InheritanceDto.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", name = "Modifier", scope = InheritanceDto.class)
    public JAXBElement<String> createInheritanceDtoModifier(String value) {
        return new JAXBElement<String>(_DeclarationDtoModifier_QNAME, String.class, InheritanceDto.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", name = "RcsServerId", scope = SearchRcsProjectsRequest.class)
    public JAXBElement<Integer> createSearchRcsProjectsRequestRcsServerId(Integer value) {
        return new JAXBElement<Integer>(_SearchRcsProjectsRequestRcsServerId_QNAME, Integer.class, SearchRcsProjectsRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", name = "Url", scope = SearchRcsProjectsRequest.class)
    public JAXBElement<String> createSearchRcsProjectsRequestUrl(String value) {
        return new JAXBElement<String>(_RcsProjectDtoUrl_QNAME, String.class, SearchRcsProjectsRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeclarationDto }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", name = "Declaration", scope = CodeEntityVersionDto.class)
    public JAXBElement<DeclarationDto> createCodeEntityVersionDtoDeclaration(DeclarationDto value) {
        return new JAXBElement<DeclarationDto>(_CodeEntityVersionDtoDeclaration_QNAME, DeclarationDto.class, CodeEntityVersionDto.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", name = "CommentEndRow", scope = CodeEntityVersionDto.class)
    public JAXBElement<Integer> createCodeEntityVersionDtoCommentEndRow(Integer value) {
        return new JAXBElement<Integer>(_CodeEntityVersionDtoCommentEndRow_QNAME, Integer.class, CodeEntityVersionDto.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", name = "ParentId", scope = CodeEntityVersionDto.class)
    public JAXBElement<Integer> createCodeEntityVersionDtoParentId(Integer value) {
        return new JAXBElement<Integer>(_CodeEntityVersionDtoParentId_QNAME, Integer.class, CodeEntityVersionDto.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", name = "CommentStartRow", scope = CodeEntityVersionDto.class)
    public JAXBElement<Integer> createCodeEntityVersionDtoCommentStartRow(Integer value) {
        return new JAXBElement<Integer>(_CodeEntityVersionDtoCommentStartRow_QNAME, Integer.class, CodeEntityVersionDto.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", name = "ChangesetIdInRcs", scope = FileGitIdentifierDto.class)
    public JAXBElement<String> createFileGitIdentifierDtoChangesetIdInRcs(String value) {
        return new JAXBElement<String>(_AssociateChangesetWithBranchRequestChangesetIdInRcs_QNAME, String.class, FileGitIdentifierDto.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", name = "BranchName", scope = FileGitIdentifierDto.class)
    public JAXBElement<String> createFileGitIdentifierDtoBranchName(String value) {
        return new JAXBElement<String>(_FileGitIdentifierDtoBranchName_QNAME, String.class, FileGitIdentifierDto.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", name = "RcsProjectUrl", scope = FileGitIdentifierDto.class)
    public JAXBElement<String> createFileGitIdentifierDtoRcsProjectUrl(String value) {
        return new JAXBElement<String>(_FileGitIdentifierDtoRcsProjectUrl_QNAME, String.class, FileGitIdentifierDto.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", name = "Url", scope = FileGitIdentifierDto.class)
    public JAXBElement<String> createFileGitIdentifierDtoUrl(String value) {
        return new JAXBElement<String>(_RcsProjectDtoUrl_QNAME, String.class, FileGitIdentifierDto.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RcsServerDto }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", name = "RcsServer", scope = GetRcsServerResponse.class)
    public JAXBElement<RcsServerDto> createGetRcsServerResponseRcsServer(RcsServerDto value) {
        return new JAXBElement<RcsServerDto>(_GetRcsServerResponseRcsServer_QNAME, RcsServerDto.class, GetRcsServerResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfBranchDto }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", name = "Branches", scope = SearchBranchesResponse.class)
    public JAXBElement<ArrayOfBranchDto> createSearchBranchesResponseBranches(ArrayOfBranchDto value) {
        return new JAXBElement<ArrayOfBranchDto>(_SearchBranchesResponseBranches_QNAME, ArrayOfBranchDto.class, SearchBranchesResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfFileGitIdentifierDto }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", name = "Identifiers", scope = GetFilesByGitIdentifiersRequest.class)
    public JAXBElement<ArrayOfFileGitIdentifierDto> createGetFilesByGitIdentifiersRequestIdentifiers(ArrayOfFileGitIdentifierDto value) {
        return new JAXBElement<ArrayOfFileGitIdentifierDto>(_GetFilesByGitIdentifiersRequestIdentifiers_QNAME, ArrayOfFileGitIdentifierDto.class, GetFilesByGitIdentifiersRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfCodeEntityVersionDto }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", name = "CodeEntityVersions", scope = SearchCodeEntitiesResponse.class)
    public JAXBElement<ArrayOfCodeEntityVersionDto> createSearchCodeEntitiesResponseCodeEntityVersions(ArrayOfCodeEntityVersionDto value) {
        return new JAXBElement<ArrayOfCodeEntityVersionDto>(_SearchCodeEntitiesResponseCodeEntityVersions_QNAME, ArrayOfCodeEntityVersionDto.class, SearchCodeEntitiesResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ChangesetDto }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", name = "Changeset", scope = GetBranchImportedHeadResponse.class)
    public JAXBElement<ChangesetDto> createGetBranchImportedHeadResponseChangeset(ChangesetDto value) {
        return new JAXBElement<ChangesetDto>(_GetBranchImportedHeadResponseChangeset_QNAME, ChangesetDto.class, GetBranchImportedHeadResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", name = "Url", scope = SearchRcsServersRequest.class)
    public JAXBElement<String> createSearchRcsServersRequestUrl(String value) {
        return new JAXBElement<String>(_RcsProjectDtoUrl_QNAME, String.class, SearchRcsServersRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfChangesetDto }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", name = "Changesets", scope = GetCodeEntityChangesetsResponse.class)
    public JAXBElement<ArrayOfChangesetDto> createGetCodeEntityChangesetsResponseChangesets(ArrayOfChangesetDto value) {
        return new JAXBElement<ArrayOfChangesetDto>(_GetCodeEntityChangesetsResponseChangesets_QNAME, ArrayOfChangesetDto.class, GetCodeEntityChangesetsResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", name = "Url", scope = EnsureRcsProjectRequest.class)
    public JAXBElement<String> createEnsureRcsProjectRequestUrl(String value) {
        return new JAXBElement<String>(_RcsProjectDtoUrl_QNAME, String.class, EnsureRcsProjectRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", name = "Content", scope = GetFileContentResponse.class)
    public JAXBElement<String> createGetFileContentResponseContent(String value) {
        return new JAXBElement<String>(_GetFileContentResponseContent_QNAME, String.class, GetFileContentResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link RcsProjectDto }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", name = "RcsProject", scope = GetChangesetRcsProjectResponse.class)
    public JAXBElement<RcsProjectDto> createGetChangesetRcsProjectResponseRcsProject(RcsProjectDto value) {
        return new JAXBElement<RcsProjectDto>(_GetRcsProjectResponseRcsProject_QNAME, RcsProjectDto.class, GetChangesetRcsProjectResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfCodeEntityVersionDto }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", name = "Versions", scope = GetChildCodeEntitiesResponse.class)
    public JAXBElement<ArrayOfCodeEntityVersionDto> createGetChildCodeEntitiesResponseVersions(ArrayOfCodeEntityVersionDto value) {
        return new JAXBElement<ArrayOfCodeEntityVersionDto>(_GetFilesByTfsIdentifiersResponseVersions_QNAME, ArrayOfCodeEntityVersionDto.class, GetChildCodeEntitiesResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", name = "Login", scope = UserDto.class)
    public JAXBElement<String> createUserDtoLogin(String value) {
        return new JAXBElement<String>(_SearchUsersRequestLogin_QNAME, String.class, UserDto.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", name = "TypeName", scope = ParameterDto.class)
    public JAXBElement<String> createParameterDtoTypeName(String value) {
        return new JAXBElement<String>(_InheritanceDtoTypeName_QNAME, String.class, ParameterDto.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", name = "Modifier", scope = ParameterDto.class)
    public JAXBElement<String> createParameterDtoModifier(String value) {
        return new JAXBElement<String>(_DeclarationDtoModifier_QNAME, String.class, ParameterDto.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", name = "Name", scope = ParameterDto.class)
    public JAXBElement<String> createParameterDtoName(String value) {
        return new JAXBElement<String>(_SearchCodeEntitiesRequestName_QNAME, String.class, ParameterDto.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", name = "TypeName", scope = ReturnValueDto.class)
    public JAXBElement<String> createReturnValueDtoTypeName(String value) {
        return new JAXBElement<String>(_InheritanceDtoTypeName_QNAME, String.class, ReturnValueDto.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", name = "Modifier", scope = ReturnValueDto.class)
    public JAXBElement<String> createReturnValueDtoModifier(String value) {
        return new JAXBElement<String>(_DeclarationDtoModifier_QNAME, String.class, ReturnValueDto.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfChangesetDto }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", name = "Changesets", scope = SearchChangesetsResponse.class)
    public JAXBElement<ArrayOfChangesetDto> createSearchChangesetsResponseChangesets(ArrayOfChangesetDto value) {
        return new JAXBElement<ArrayOfChangesetDto>(_GetCodeEntityChangesetsResponseChangesets_QNAME, ArrayOfChangesetDto.class, SearchChangesetsResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfRcsServerDto }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", name = "RcsServers", scope = SearchRcsServersResponse.class)
    public JAXBElement<ArrayOfRcsServerDto> createSearchRcsServersResponseRcsServers(ArrayOfRcsServerDto value) {
        return new JAXBElement<ArrayOfRcsServerDto>(_SearchRcsServersResponseRcsServers_QNAME, ArrayOfRcsServerDto.class, SearchRcsServersResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfRcsProjectDto }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", name = "RcsProjects", scope = SearchRcsProjectsResponse.class)
    public JAXBElement<ArrayOfRcsProjectDto> createSearchRcsProjectsResponseRcsProjects(ArrayOfRcsProjectDto value) {
        return new JAXBElement<ArrayOfRcsProjectDto>(_SearchRcsProjectsResponseRcsProjects_QNAME, ArrayOfRcsProjectDto.class, SearchRcsProjectsResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UserDto }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", name = "User", scope = GetUserResponse.class)
    public JAXBElement<UserDto> createGetUserResponseUser(UserDto value) {
        return new JAXBElement<UserDto>(_GetUserResponseUser_QNAME, UserDto.class, GetUserResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfFileVersionDto }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", name = "FileVersions", scope = SearchFilesResponse.class)
    public JAXBElement<ArrayOfFileVersionDto> createSearchFilesResponseFileVersions(ArrayOfFileVersionDto value) {
        return new JAXBElement<ArrayOfFileVersionDto>(_GetChangedFilesResponseFileVersions_QNAME, ArrayOfFileVersionDto.class, SearchFilesResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ChangesetDto }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", name = "Changeset", scope = GetBranchQueuedHeadResponse.class)
    public JAXBElement<ChangesetDto> createGetBranchQueuedHeadResponseChangeset(ChangesetDto value) {
        return new JAXBElement<ChangesetDto>(_GetBranchImportedHeadResponseChangeset_QNAME, ChangesetDto.class, GetBranchQueuedHeadResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", name = "EntityId", scope = SearchFilesRequest.class)
    public JAXBElement<Integer> createSearchFilesRequestEntityId(Integer value) {
        return new JAXBElement<Integer>(_SearchCodeEntitiesRequestEntityId_QNAME, Integer.class, SearchFilesRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", name = "UrlStart", scope = SearchFilesRequest.class)
    public JAXBElement<String> createSearchFilesRequestUrlStart(String value) {
        return new JAXBElement<String>(_SearchFilesRequestUrlStart_QNAME, String.class, SearchFilesRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfUserDto }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", name = "Users", scope = SearchUsersResponse.class)
    public JAXBElement<ArrayOfUserDto> createSearchUsersResponseUsers(ArrayOfUserDto value) {
        return new JAXBElement<ArrayOfUserDto>(_SearchUsersResponseUsers_QNAME, ArrayOfUserDto.class, SearchUsersResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", name = "IdInRcs", scope = QueueChangesetForImportRequest.class)
    public JAXBElement<String> createQueueChangesetForImportRequestIdInRcs(String value) {
        return new JAXBElement<String>(_ChangesetDtoIdInRcs_QNAME, String.class, QueueChangesetForImportRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", name = "Ancestor2IdInRcs", scope = QueueChangesetForImportRequest.class)
    public JAXBElement<String> createQueueChangesetForImportRequestAncestor2IdInRcs(String value) {
        return new JAXBElement<String>(_QueueChangesetForImportRequestAncestor2IdInRcs_QNAME, String.class, QueueChangesetForImportRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfQueuedFileVersionDto }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", name = "ChangedFiles", scope = QueueChangesetForImportRequest.class)
    public JAXBElement<ArrayOfQueuedFileVersionDto> createQueueChangesetForImportRequestChangedFiles(ArrayOfQueuedFileVersionDto value) {
        return new JAXBElement<ArrayOfQueuedFileVersionDto>(_QueueChangesetForImportRequestChangedFiles_QNAME, ArrayOfQueuedFileVersionDto.class, QueueChangesetForImportRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link byte[]}{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", name = "FilesBlob", scope = QueueChangesetForImportRequest.class)
    public JAXBElement<byte[]> createQueueChangesetForImportRequestFilesBlob(byte[] value) {
        return new JAXBElement<byte[]>(_QueueChangesetForImportRequestFilesBlob_QNAME, byte[].class, QueueChangesetForImportRequest.class, ((byte[]) value));
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", name = "Message", scope = QueueChangesetForImportRequest.class)
    public JAXBElement<String> createQueueChangesetForImportRequestMessage(String value) {
        return new JAXBElement<String>(_ChangesetDtoMessage_QNAME, String.class, QueueChangesetForImportRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", name = "Committer", scope = QueueChangesetForImportRequest.class)
    public JAXBElement<String> createQueueChangesetForImportRequestCommitter(String value) {
        return new JAXBElement<String>(_ChangesetDtoCommitter_QNAME, String.class, QueueChangesetForImportRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", name = "Ancestor1IdInRcs", scope = QueueChangesetForImportRequest.class)
    public JAXBElement<String> createQueueChangesetForImportRequestAncestor1IdInRcs(String value) {
        return new JAXBElement<String>(_QueueChangesetForImportRequestAncestor1IdInRcs_QNAME, String.class, QueueChangesetForImportRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", name = "ImportedHeadId", scope = BranchDto.class)
    public JAXBElement<Integer> createBranchDtoImportedHeadId(Integer value) {
        return new JAXBElement<Integer>(_BranchDtoImportedHeadId_QNAME, Integer.class, BranchDto.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", name = "QueuedHeadId", scope = BranchDto.class)
    public JAXBElement<Integer> createBranchDtoQueuedHeadId(Integer value) {
        return new JAXBElement<Integer>(_BranchDtoQueuedHeadId_QNAME, Integer.class, BranchDto.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", name = "Name", scope = BranchDto.class)
    public JAXBElement<String> createBranchDtoName(String value) {
        return new JAXBElement<String>(_SearchCodeEntitiesRequestName_QNAME, String.class, BranchDto.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BranchDto }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", name = "Branch", scope = GetBranchResponse.class)
    public JAXBElement<BranchDto> createGetBranchResponseBranch(BranchDto value) {
        return new JAXBElement<BranchDto>(_GetBranchResponseBranch_QNAME, BranchDto.class, GetBranchResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", name = "ContentIdentifier", scope = QueuedFileVersionDto.class)
    public JAXBElement<String> createQueuedFileVersionDtoContentIdentifier(String value) {
        return new JAXBElement<String>(_QueuedFileVersionDtoContentIdentifier_QNAME, String.class, QueuedFileVersionDto.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", name = "FileUrl", scope = QueuedFileVersionDto.class)
    public JAXBElement<String> createQueuedFileVersionDtoFileUrl(String value) {
        return new JAXBElement<String>(_QueuedFileVersionDtoFileUrl_QNAME, String.class, QueuedFileVersionDto.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", name = "ChangesetIdInRcs", scope = IsChangesetQueuedOrImportedRequest.class)
    public JAXBElement<String> createIsChangesetQueuedOrImportedRequestChangesetIdInRcs(String value) {
        return new JAXBElement<String>(_AssociateChangesetWithBranchRequestChangesetIdInRcs_QNAME, String.class, IsChangesetQueuedOrImportedRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfChangesetDto }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", name = "Changesets", scope = GetFileChangesetsResponse.class)
    public JAXBElement<ArrayOfChangesetDto> createGetFileChangesetsResponseChangesets(ArrayOfChangesetDto value) {
        return new JAXBElement<ArrayOfChangesetDto>(_GetCodeEntityChangesetsResponseChangesets_QNAME, ArrayOfChangesetDto.class, GetFileChangesetsResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", name = "Type", scope = RcsServerDto.class)
    public JAXBElement<String> createRcsServerDtoType(String value) {
        return new JAXBElement<String>(_RcsServerDtoType_QNAME, String.class, RcsServerDto.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", name = "Url", scope = RcsServerDto.class)
    public JAXBElement<String> createRcsServerDtoUrl(String value) {
        return new JAXBElement<String>(_RcsProjectDtoUrl_QNAME, String.class, RcsServerDto.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FileVersionDto }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", name = "Version", scope = GetFileResponse.class)
    public JAXBElement<FileVersionDto> createGetFileResponseVersion(FileVersionDto value) {
        return new JAXBElement<FileVersionDto>(_GetCodeEntityResponseVersion_QNAME, FileVersionDto.class, GetFileResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfFileTfsIdentifierDto }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", name = "Identifiers", scope = GetFilesByTfsIdentifiersRequest.class)
    public JAXBElement<ArrayOfFileTfsIdentifierDto> createGetFilesByTfsIdentifiersRequestIdentifiers(ArrayOfFileTfsIdentifierDto value) {
        return new JAXBElement<ArrayOfFileTfsIdentifierDto>(_GetFilesByGitIdentifiersRequestIdentifiers_QNAME, ArrayOfFileTfsIdentifierDto.class, GetFilesByTfsIdentifiersRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", name = "RcsServerType", scope = EnsureRcsServerRequest.class)
    public JAXBElement<String> createEnsureRcsServerRequestRcsServerType(String value) {
        return new JAXBElement<String>(_EnsureRcsServerRequestRcsServerType_QNAME, String.class, EnsureRcsServerRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", name = "Url", scope = EnsureRcsServerRequest.class)
    public JAXBElement<String> createEnsureRcsServerRequestUrl(String value) {
        return new JAXBElement<String>(_RcsProjectDtoUrl_QNAME, String.class, EnsureRcsServerRequest.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ChangesetDto }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", name = "Changeset", scope = GetChangesetResponse.class)
    public JAXBElement<ChangesetDto> createGetChangesetResponseChangeset(ChangesetDto value) {
        return new JAXBElement<ChangesetDto>(_GetBranchImportedHeadResponseChangeset_QNAME, ChangesetDto.class, GetChangesetResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", name = "FullUrl", scope = FileTfsIdentifierDto.class)
    public JAXBElement<String> createFileTfsIdentifierDtoFullUrl(String value) {
        return new JAXBElement<String>(_FileTfsIdentifierDtoFullUrl_QNAME, String.class, FileTfsIdentifierDto.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", name = "ChangesetIdInRcs", scope = FileTfsIdentifierDto.class)
    public JAXBElement<String> createFileTfsIdentifierDtoChangesetIdInRcs(String value) {
        return new JAXBElement<String>(_AssociateChangesetWithBranchRequestChangesetIdInRcs_QNAME, String.class, FileTfsIdentifierDto.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", name = "RcsServerUrl", scope = FileTfsIdentifierDto.class)
    public JAXBElement<String> createFileTfsIdentifierDtoRcsServerUrl(String value) {
        return new JAXBElement<String>(_FileTfsIdentifierDtoRcsServerUrl_QNAME, String.class, FileTfsIdentifierDto.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", name = "Url", scope = FileVersionDto.class)
    public JAXBElement<String> createFileVersionDtoUrl(String value) {
        return new JAXBElement<String>(_RcsProjectDtoUrl_QNAME, String.class, FileVersionDto.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfstring }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", name = "Folders", scope = SearchFoldersResponse.class)
    public JAXBElement<ArrayOfstring> createSearchFoldersResponseFolders(ArrayOfstring value) {
        return new JAXBElement<ArrayOfstring>(_SearchFoldersResponseFolders_QNAME, ArrayOfstring.class, SearchFoldersResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.datacontract.org/2004/07/Gratex.PerConIK.AstRcs.Svc.Interfaces", name = "Content", scope = GetCodeEntityContentResponse.class)
    public JAXBElement<String> createGetCodeEntityContentResponseContent(String value) {
        return new JAXBElement<String>(_GetFileContentResponseContent_QNAME, String.class, GetCodeEntityContentResponse.class, value);
    }

}
